#include<stdio.h>
#include<stdlib.h>
struct Frames
{
    int size;
    int status;
    struct Frames * next;
};
typedef struct Frames frames;

//Insert
void insert(frames **head, frames **tail, int stat, int sz)
{
    frames* new_node = (frames*) malloc (sizeof(frames));
    new_node->status=stat;
    new_node->size=sz;
    new_node->next=NULL;
    if((*head)==NULL)
    {
        (*head)=(*tail)=new_node;
    }
    else
    {
        (*tail)->next=new_node;
        (*tail)=new_node;
    }
}

//Best-Fit
// 最佳适应算法(Best Fit,BF)
int Best_Fit(frames **head, frames **tail, int pid, int s)
{
    int newsize;
    frames *temp = *head;
    frames *p=NULL;
    while(temp!=NULL)
    {
        if((temp->status==-1)&&(temp->size>=s))
        {
            if(p==NULL)
                p=temp;
            else
            {
                if((p->size)>(temp->size)){
                    p=temp;
                }
            }
        }
        temp=temp->next;
    }
    newsize=p->size-s;
    if(newsize>0)
    {
        frames *newnode=(frames*)malloc(sizeof(frames));
        newnode->next=p->next;
        p->next=newnode;
        p->status=pid;
        p->size=s;
        newnode->size=newsize;
        newnode->status=-1;
    }
    else if(newsize==0)
    {
        p->size=s;
        p->status=pid;
    }
    if(p==NULL)
    {
        return 0;
    }
    else return 1;
}

//Worst-Fit
// 最h坏适应算法(Best Fit,BF)
void worst_fit(frames **head,frames **tail,int pid,int size)
{
    frames *temp=*(head);
    frames *p=NULL;
    while(temp!=NULL){
        if((temp->status==-1)&&(temp->size>=size)){
            if(p==NULL){
                p=temp;
            }
            
            else if((p->size) < (temp->size))
                p=temp;
        }
        temp=temp->next;
    }
    if(p->size > size){
        p->status=pid;
        frames *temp1=(frames*)malloc(sizeof(frames));
        temp1->size=(p->size)-size;
        temp1->status=-1;
        temp1->next=p->next;
        p->size=size;
        p->next=temp1;
    }
    else if(p->size == size){
        p->status=pid;
    }
    else
        // printf("Cannot insert!\n");
        printf("不能插入!\n");
    
}

//First-Fit
//首次适应算法(First Fit, FF)
int first_fit(frames** head, int pid, int size)
{
    frames* temp=*head;
    int k,flag=1;
    frames* new_node = (frames*) malloc (sizeof(frames));
    new_node->status=-1;
    while(temp!=NULL &&  size>temp->size || temp->status!=-1)
    {
        temp=temp->next;
    }
    if(temp==NULL)
    {
        flag=0;
    }
    else if(size!=temp->size)
    {
        k=(temp)->size-size;
        new_node->size=k;
        temp->status=pid;
        temp->size=size;
        new_node->next=temp->next;
        temp->next=new_node;
        printf("%d",temp->size);
    }
    else if(size==temp->size)
    {
        temp->status=pid;
        free(new_node);
    }
    return flag;
}

//内存回收
void dealloc(frames** head,int pid)
{
    frames* temp=*head;
    int flag=0;
    while(temp!=NULL)
    {
        if(temp->status==pid)
        {
            temp->status=-1;
            flag=1;
        }
        else
        {
            temp=temp->next;
        }
    }
    if(flag==0)
        // printf("No such process was found!\n");
        printf("进程未找到!\n");
}

//Display
//打印内存分区状态
void print_list(frames ** head)
{
    frames * node=(*head);
    printf("内存池状态（-1空闲， 1已分配）");
    printf("\n\n状态\t大小");
    while (node!=NULL)
    {
        printf("\n\n%d\t%d\n",node->status,node->size);
        node=node->next;
    }
}
//Coalesce �ϲ� 
//分配内存
void coalesce(frames** head)
{
    frames* temp= *head;
    frames* temp1=NULL;
    while(temp!=NULL)
    {
        if(temp->status==-1)
        {
            temp1=temp->next;
            while(temp1!=NULL && temp1->status==-1)
            {
                temp->size = (temp->size) + (temp1->size);
                temp->next=temp1->next;
                temp1=temp->next;
            }
            temp=temp1;
        }
        else
        {
            temp=temp->next;
        }
    }
}

int main()
{
    frames *free_head = NULL;
    frames *free_tail = NULL;
    //Memory
    // printf("Enter Number of Partitions: ");
    printf("输入分区数量: ");
    int i,n;
    int ch, choice;
    scanf("%d",&n);
    int a,b,c,d;
    for(i=0;i<n;i++ )
    {
        // printf("Enter start address of frame %d: ",i);
        printf("输入分区起始地址 %d: ",i);
        scanf("%d",&a);
        // printf("Enter end address of frame %d: ",i);
        printf("输入分区结束地址 %d: ",i);
        scanf("%d",&b);
        c = -1;
        d = (b) - (a);
        insert(&free_head,&free_tail,c,d);
    }
    // printf("FREE POOL MEMORY");
    // printf("内存池状态（-1空闲， 1已分配）");
    print_list(&free_head);
    printf("\n\n");
    do
    {
        // printf("\nMenu:");
        printf("\n菜单:");
        // printf("\n1.Entry/Allocate");
        printf("\n1.进入/分配内存");
        // printf("\n2.Exit/Deallocate");
        printf("\n2.退出/释放内存");
        // printf("\n3.Display");
        printf("\n3.内存池状态");
        // printf("\n4.Coalesce");
        printf("\n4.合并空闲分区");
        // printf("\n5.Exit");
        printf("\n5.退出");
        int k;
        // printf("\nEnter Choice: ");
        printf("\n输入选择: ");
        scanf("%d",&ch);
        switch(ch)
        {
            case 1:
            {
                // printf("Enter size of process: ");
                printf("输入进程大小: ");
                int sz;
                scanf("%d",&sz);
                // printf("Enter PID: ");
                printf("输入进程ID: ");
                int p_pid;
                scanf("%d",&p_pid);
                do
                {
                    // printf("\nMenu:");
                    printf("\n菜单:");
                    // printf("\n1.First Fit");
                    printf("\n1.首次适应算法(First Fit, FF)");
                    printf("\n2.最佳适应算法(Best Fit, BF)");
                    printf("\n3.最坏适应算法(Worst Fit, WF)");
                    // printf("\n4.Exit");
                    printf("\n4.退出");
                    // printf("\nEnter Choice: ");
                    printf("\n输入选择: ");
                    scanf("%d",&choice);
                    switch(choice)
                    {
                        case 1:
                        {
                            k=first_fit(&free_head,p_pid,sz);
                            // if(!k) printf("\nProcess cannot be allocated!");
                            if(!k) printf("\n进程不能被分配内存!!");
                            print_list(&free_head);
                            break;
                        }
                        case 2:
                        {
                            k=Best_Fit(&free_head,&free_tail,p_pid,sz);
                            // if(!k) printf("\nProcess cannot be allocated!");
                            if(!k) printf("\n进程不能被分配内存!");
                            print_list(&free_head);
                            break;
                        }
                        case 3:
                        {
                            worst_fit(&free_head,&free_tail,p_pid,sz);
                            print_list(&free_head);
                            break;
                        }
                        case 4:
                        {
                            // printf("\nExit!\n");
                            printf("\n退出!\n");
                            break;
                        }
                        // default : printf("\nInvalid Choice!");
                        default : printf("\n请输入正确选项!");
                    }
                }while(0);
                break;
            }
            case 2:
            {
                // printf("\nEnter PID to deallocate: ");
                printf("\n输入需要释放的进程ID: ");
                int p_pid;
                scanf("%d",&p_pid);
                dealloc(&free_head,p_pid);
                break;
            }
            case 3:
            {
                print_list(&free_head);
                break;
            }
            case 4:
            {
                coalesce(&free_head);
                print_list(&free_head);
                break;
            }
            case 5: {
                // printf("\nExit!\n");
                printf("\n退出!\n");
                break;
            }
            // default : printf("\nInvalid Choice!");
            default : printf("\n请输入正确选项!");
        }
    }while(ch!=5);
    return 0;
}

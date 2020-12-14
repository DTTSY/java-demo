CREATE TABLE new_car (
  ID int identity(1,1) NOT NULL,
  Type varchar(50) ,
  CarId varchar(20),
  Brand varchar(50),
  Model varchar(50),
  Seats int,
  IsAuto varchar(10),
  Power varchar(20),
  Color varchar(20),
  Rent int,
  Status varchar(20)
 )
 go
 
insert into new_car values('������','��A87E3A','����', ';��L2015��', 5 ,'��' ,'2.0T' ,'��ɫ', 300 ,'����')
insert into new_car values('������', '��A71730', '����', '������2018��', 5 ,'��', '1.8T', '��ɫ', 300, '���δ��')
insert into new_car values('SUV', '��A5DD15', '����', 'X6', 5, '��', '3.0T', '��ɫ' ,500, '���δ��')
insert into new_car values('������', '��AHG393', '���', 'GL8', 5,'��', '2.0', '��ɫ', 700 ,'����')
insert into new_car values('�綯��', '��AAN116', '��˹��', 'MODEL3', 5, '��', '2.0T', '��ɫ', 400, 'ά��')
insert into new_car values('�߶˳�', '��AC0967', '��ʱ��', '����', 5, '��', '3.0T', '��ɫ', 800, '����')
go
select * from new_car
go

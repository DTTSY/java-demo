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
 
insert into new_car values('经济型','川A87E3A','大众', '途观L2015款', 5 ,'是' ,'2.0T' ,'白色', 300 ,'可租')
insert into new_car values('舒适性', '川A71730', '丰田', '凯美瑞2018款', 5 ,'是', '1.8T', '黑色', 300, '租出未还')
insert into new_car values('SUV', '川A5DD15', '宝马', 'X6', 5, '是', '3.0T', '红色' ,500, '租出未还')
insert into new_car values('商务型', '川AHG393', '别克', 'GL8', 5,'是', '2.0', '白色', 700 ,'可租')
insert into new_car values('电动型', '川AAN116', '特斯拉', 'MODEL3', 5, '是', '2.0T', '黑色', 400, '维修')
insert into new_car values('高端车', '川AC0967', '保时捷', '卡宴', 5, '是', '3.0T', '红色', 800, '可租')
go
select * from new_car
go

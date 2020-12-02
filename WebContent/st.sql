CREATE TABLE SC (
    Sno VARCHAR(7),
    Cno VARCHAR(10),
    Grade INT,
    PRIMARY KEY (Sno, Cno),
    CHECK (Grade>=0 AND  Grade<=100),
    FOREIGN KEY (Sno) REFERENCES Student(id),
    FOREIGN KEY (Cno) REFERENCES Course(id)
);

ALTER TABLE SC ADD XKLB char(4);
ALTER TABLE SC ALTER COLUMN XKLB char(6);
alter table SC ADD CONSTRAINT CK_SC CHECK(XKLB IN ('必修','选修','限选'))
--������Ʈ �׽�Ʈ ����
delete from projectmember where prj_domain = 'domain1234';
delete from projects where prj_domain = 'domain1234';
delete from members where memb_mail = 'zzz@zzz.com';

commit;

select * from projectmember where prj_domain = 'domain1234';
select * from projects where prj_domain = 'domain1234';
select * from members where memb_mail = 'zzz@zzz.com';
--������Ʈ �׽�Ʈ ����

DROP TABLE members;
DROP TABLE projects;
DROP TABLE projectmember;

CREATE TABLE members (
    memb_mail	    VARCHAR2(70)		PRIMARY KEY,     
    memb_name	    VARCHAR2(50)		NOT NULL,
    memb_pwd	    VARCHAR2(200)		NOT NULL,
    memb_ogFile	    VARCHAR2(1000)		NULL,
    memb_savedFile	VARCHAR2(1000)	    NULL,
    memb_work	    VARCHAR2(1000)		NULL,
    memb_phone	    VARCHAR2(100)		NOT NULL,
    enabled	        NUMBER	            DEFAULT 0	        CHECK(enabled IN (0,1)),
    rolename        VARCHAR2(20)        DEFAULT 'ROLE_USER' CHECK(rolename IN ('ROLE_USER','ROLE_ADMIN')) 
);

CREATE TABLE projects (
    prj_domain	VARCHAR2(10)		        PRIMARY KEY,
    prj_name	VARCHAR2(20)		        NOT NULL,
    prj_date	DATE	        DEFAULT     SYSDATE	NOT NULL,
    prj_enabled	NUMBER	        DEFAULT 1	CHECK( prj_enabled IN (0,1))
);


CREATE TABLE projectmember (
    prj_domain	    VARCHAR2(10)		REFERENCES projects(prj_domain) NOT NULL ,
    memb_mail	    VARCHAR2(70)		REFERENCES members(memb_mail) NOT NULL,
    pjmb_right	    VARCHAR2(10)	    DEFAULT 'member' NOT NULL,

    CONSTRAINT PJTMEMB_PK_DOMAIN_MEMBMAIL PRIMARY KEY(prj_domain, memb_mail)   
);

--��� �Է�

INSERT INTO members (memb_mail, memb_name, memb_pwd, memb_ogFile,  memb_savedFile, memb_work, memb_phone, enabled)
VALUES ('aaa@aaa.com', 'ȫ�浿', '1111', 'photo.jpg', 'man01.jpg', 'DB����', '010-111-1111', 1);

INSERT INTO members (memb_mail, memb_name, memb_pwd, memb_ogFile,  memb_savedFile, memb_work, memb_phone, enabled)
VALUES ('bbb@bbb.com', '������', '2222', 'photo.jpg', 'man02.jpg', 'DB����', '010-222-2222', 1);

INSERT INTO members (memb_mail, memb_name, memb_pwd, memb_ogFile,  memb_savedFile, memb_work, memb_phone, enabled)
VALUES ('ccc@ccc.com', '������', '3333', 'photo.jpg', 'man03.jpg', 'DB����', '010-333-3333', 1);

INSERT INTO members (memb_mail, memb_name, memb_pwd, memb_ogFile,  memb_savedFile, memb_work, memb_phone, enabled)
VALUES ('ddd@ddd.com', '���ؼ�', '4444', 'photo.jpg', 'man04.jpg', 'DB����', '010-444-4444', 1);

INSERT INTO members (memb_mail, memb_name, memb_pwd, memb_ogFile,  memb_savedFile, memb_work, memb_phone, enabled)
VALUES ('eee@eee.com', '������', '5555', 'photo.jpg', 'man05.jpg', 'DB����', '010-555-5555', 1);

INSERT INTO members (memb_mail, memb_name, memb_pwd, memb_ogFile,  memb_savedFile, memb_work, memb_phone, enabled)
VALUES ('fff@fff.com', '�Ǽ���', '6666', 'photo.jpg', 'man06.jpg', 'DB����', '010-666-6666', 1);

INSERT INTO members (memb_mail, memb_name, memb_pwd, memb_ogFile,  memb_savedFile, memb_work, memb_phone, enabled)
VALUES ('ggg@ggg.com', '�����', '7777', 'photo.jpg', 'man07.jpg', 'DB����', '010-777-7777', 1);

INSERT INTO members (memb_mail, memb_name, memb_pwd, memb_ogFile,  memb_savedFile, memb_work, memb_phone, enabled)
VALUES ('hhh@hhh.com', '�����', '8888', '������8.jpg', 'man08.jpg', 'DB����', '010-888-8888', 1);

INSERT INTO members (memb_mail, memb_name, memb_pwd, memb_ogFile,  memb_savedFile, memb_work, memb_phone, enabled)
VALUES ('iii@iii.com', '������', '9999', '������9.jpg', 'man09.jpg', 'DB����', '010-999-9999', 1);

INSERT INTO members (memb_mail, memb_name, memb_pwd, memb_ogFile,  memb_savedFile, memb_work, memb_phone, enabled)
VALUES ('jjj@jjj.com', 'ȫ��ǥ', '0000', 'photo.jpg', 'man10.jpg', 'DB����', '010-000-0000', 1);

--������Ʈ �Է�
INSERT INTO projects (prj_domain, prj_name, prj_enabled) 
VALUES ('scit41','������Ʈ #01',0);

INSERT INTO projects (prj_domain, prj_name,  prj_enabled) 
VALUES ('scit42','������Ʈ #02',0);

INSERT INTO projects (prj_domain, prj_name,  prj_enabled) 
VALUES ('scit43','������Ʈ #03',0);

INSERT INTO projects (prj_domain, prj_name,  prj_enabled) 
VALUES ('scit44','������Ʈ #04',0);

INSERT INTO projects (prj_domain, prj_name,  prj_enabled) 
VALUES ('scit45','������Ʈ #05',0);

INSERT INTO projects (prj_domain, prj_name,  prj_enabled) 
VALUES ('scit46','������Ʈ #06',1);

INSERT INTO projects (prj_domain, prj_name,  prj_enabled) 
VALUES ('scit47','������Ʈ #07',1);

INSERT INTO projects (prj_domain, prj_name,  prj_enabled) 
VALUES ('scit48','������Ʈ #08',1);

INSERT INTO projects (prj_domain, prj_name,  prj_enabled) 
VALUES ('scit49','������Ʈ #09',1);

INSERT INTO projects (prj_domain, prj_name,  prj_enabled) 
VALUES ('scit50','������Ʈ #10',1);

--������Ʈ��� �Է�
insert into projectmember 
(prj_domain,memb_mail,pjmb_right) 
(select p.prj_domain, m.memb_mail,'member'
from projects p,members m
);

update projectmember set pjmb_right ='PM'
where memb_mail = 'aaa@aaa.com';

commit;

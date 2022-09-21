DELETE FROM notice;

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
(SELECT notice_seq.nextval, 'scit41' ,'���������Դϴ�.',  memb_mail , '�������� �����Դϴ�.\n\r �������� �����Դϴ�. \n\r�������� �����Դϴ�.', 
            'dog01.jpg', 'scit41.jpg', 0
	FROM members
);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
(SELECT notice_seq.nextval, 'scit43' ,'���������Դϴ�.',  memb_mail , '�������� �����Դϴ�.\n\r �������� �����Դϴ�. \n\r�������� �����Դϴ�.', 
            'dog01.jpg', 'scit41.jpg', 0
	FROM members
);

select count(*) from notice where PRJ_DOMAIN = 'scit43';

update notice set NOTICE_NAME = NOTICE_WRITER ||'�� ' ||PRJ_DOMAIN|| '�� �ø�'|| '���������Դϴ�.';
update notice set NOTICE_CONTENTS = NOTICE_CONTENTS||'  ' ||NOTICE_WRITER ||'�� ' ||PRJ_DOMAIN|| '�� �ø�'|| '���������� �����Դϴ�.';

commit;


SELECT notice_seq.nextval, 'scit41' ,'���������Դϴ�.',  memb_mail , '�������� �����Դϴ�.\n\r �������� �����Դϴ�. \n\r�������� �����Դϴ�.', 
            'dog01.jpg', 'scit41.jpg', 0
	FROM members;

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit41','���������Դϴ�.', 'aaa@aaa.com', '�������� �����Դϴ�.', 'dog01.jpg', 'scit41.jpg', 0);



INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit42','��������02', '��02', '�������� �ۼ�02', 'dog02.jpg', 'scit42.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit43','��������03', '��03', '�������� �ۼ�03', 'dog03.jpg', 'scit43.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_DATE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit44','��������04', '��04', '�������� �ۼ�04', 'dog04.jpg', 'scit44.jpg', sysdate, 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit45','��������05', '��05', '�������� �ۼ�05', 'dog05.jpg', 'scit45.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit46','��������06', '��06', '�������� �ۼ�06', 'dog06.jpg', 'scit46.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit47','��������07', '��07', '�������� �ۼ�07', 'dog07.jpg', 'scit47.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit48','��������08', '��08', '�������� �ۼ�08', 'dog08.jpg', 'scit48.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit49','��������09', '��09', '�������� �ۼ�09', 'dog09.jpg', 'scit49.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit50','��������10', '��10', '�������� �ۼ�10', 'dog10.jpg', 'scit50.jpg', 0);

select prj_domain,  count(prj_domain) from notice
group by prj_domain
order by prj_domain;

	SELECT 
 		notice_seq, n.prj_domain, notice_name, notice_writer, notice_contents
		, notice_ogFilr, notice_saveFile
		, to_char(notice_date, 'YYYY-MM-DD HH24:MI:SS') notice_date
		, notice_hits, memb_name, prj_name
	FROM 
		notice n, members m, projects p
	WHERE 
		n.prj_domain =  p.prj_domain
		AND n.notice_writer = m.memb_mail;
        
	SELECT 
 		notice_seq, n.prj_domain, notice_name, notice_writer, notice_contents
		, notice_ogFilr, notice_saveFile
		, to_char(notice_date, 'YYYY-MM-DD HH24:MI:SS') notice_date
		, notice_hits, memb_name, prj_name
	FROM 
		notice n, members m, projects p
	WHERE 
		n.prj_domain =  p.prj_domain
		AND n.notice_writer = m.memb_mail
		AND notice_seq = 214;
        
    SELECT 
		count(*)	
	FROM 
		notice n, members m, projects p
	WHERE 
		n.prj_domain =  p.prj_domain
		AND n.notice_writer = m.memb_mail;

    update notice set notice_date = sysdate;
commit;

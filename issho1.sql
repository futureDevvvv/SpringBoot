DELETE FROM notice;
DELETE FROM noticecomment;

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
(SELECT notice_seq.nextval, 'scit41' ,'공지사항입니다.',  memb_mail , '공지사항 내용입니다.\n\r 공지사항 내용입니다. \n\r공지사항 내용입니다.', 
            'dog01.jpg', 'scit41.jpg', 0
	FROM members
);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
(SELECT notice_seq.nextval, 'scit43' ,'공지사항입니다.',  memb_mail , '공지사항 내용입니다.\n\r 공지사항 내용입니다. \n\r공지사항 내용입니다.', 
            'dog01.jpg', 'scit41.jpg', 0
	FROM members
);

select count(*) from notice where PRJ_DOMAIN = 'scit43';

update notice set NOTICE_NAME = NOTICE_WRITER ||'가 ' ||PRJ_DOMAIN|| '에 올린'|| '공지사항입니다.';
update notice set NOTICE_CONTENTS = NOTICE_CONTENTS||'  ' ||NOTICE_WRITER ||'가 ' ||PRJ_DOMAIN|| '에 올린'|| '공지사항의 내용입니다.';

commit;


SELECT notice_seq.nextval, 'scit41' ,'공지사항입니다.',  memb_mail , '공지사항 내용입니다.\n\r 공지사항 내용입니다. \n\r공지사항 내용입니다.', 
            'dog01.jpg', 'scit41.jpg', 0
	FROM members;

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit41','공지사항입니다.', 'aaa@aaa.com', '공지사항 내용입니다.', 'dog01.jpg', 'scit41.jpg', 0);



INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit42','공지사항02', '김02', '공지사항 작성02', 'dog02.jpg', 'scit42.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit43','공지사항03', '김03', '공지사항 작성03', 'dog03.jpg', 'scit43.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_DATE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit44','공지사항04', '김04', '공지사항 작성04', 'dog04.jpg', 'scit44.jpg', sysdate, 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit45','공지사항05', '김05', '공지사항 작성05', 'dog05.jpg', 'scit45.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit46','공지사항06', '김06', '공지사항 작성06', 'dog06.jpg', 'scit46.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit47','공지사항07', '김07', '공지사항 작성07', 'dog07.jpg', 'scit47.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit48','공지사항08', '김08', '공지사항 작성08', 'dog08.jpg', 'scit48.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit49','공지사항09', '김09', '공지사항 작성09', 'dog09.jpg', 'scit49.jpg', 0);

INSERT INTO notice 
(NOTICE_SEQ, PRJ_DOMAIN, NOTICE_NAME, NOTICE_WRITER, NOTICE_CONTENTS, 
NOTICE_OGFILR, NOTICE_SAVEFILE, NOTICE_HITS)
VALUES (notice_seq.nextval, 'scit50','공지사항10', '김10', '공지사항 작성10', 'dog10.jpg', 'scit50.jpg', 0);

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

INSERT INTO noticecomment 
(noticeCmt_seq, notice_seq, noticeCmt_writer, noticeCmt_contents, noticeCmt_date)
VALUES(noticeCmt_seq.nextval, 31, '김01', '댓글 내용입니다.01', sysdate);

INSERT INTO noticecomment 
(noticeCmt_seq, notice_seq, noticeCmt_writer, noticeCmt_contents, noticeCmt_date)
VALUES(noticeCmt_seq.nextval, 31,'김01', '댓글 내용입니다.02', sysdate);

INSERT INTO noticecomment 
(noticeCmt_seq, notice_seq, noticeCmt_writer, noticeCmt_contents, noticeCmt_date)
VALUES(noticeCmt_seq.nextval, 31,'김01', '댓글 내용입니다.03', sysdate);

INSERT INTO noticecomment 
(noticeCmt_seq, notice_seq, noticeCmt_writer, noticeCmt_contents, noticeCmt_date)
VALUES(noticeCmt_seq.nextval, 31,'김01', '댓글 내용입니다.04', sysdate);

INSERT INTO noticecomment 
(noticeCmt_seq, notice_seq, noticeCmt_writer, noticeCmt_contents, noticeCmt_date)
VALUES(noticeCmt_seq.nextval, 31,'김01', '댓글 내용입니다.05', sysdate);

INSERT INTO noticecomment 
(noticeCmt_seq, notice_seq, noticeCmt_writer, noticeCmt_contents, noticeCmt_date)
VALUES(noticeCmt_seq.nextval, 31,'김01', '댓글 내용입니다.06', sysdate);

INSERT INTO noticecomment 
(noticeCmt_seq, notice_seq, noticeCmt_writer, noticeCmt_contents, noticeCmt_date)
VALUES(noticeCmt_seq.nextval, 31,'김01', '댓글 내용입니다.07', sysdate);

INSERT INTO noticecomment 
(noticeCmt_seq, notice_seq, noticeCmt_writer, noticeCmt_contents, noticeCmt_date)
VALUES(noticeCmt_seq.nextval, 31,'김01', '댓글 내용입니다.08', sysdate);

INSERT INTO noticecomment 
(noticeCmt_seq, notice_seq, noticeCmt_writer, noticeCmt_contents, noticeCmt_date)
VALUES(noticeCmt_seq.nextval, 31,'김01', '댓글 내용입니다.09', sysdate);

INSERT INTO noticecomment 
(noticeCmt_seq, notice_seq, noticeCmt_writer, noticeCmt_contents, noticeCmt_date)
VALUES(noticeCmt_seq.nextval, 31,'김01', '댓글 내용입니다.10', sysdate);

select * from notice;

select notice_name, memb_name 
FROM notice n, members m
where n.notice_writer = m.memb_name
AND m.memb_name like '%김지윤%';

	SELECT 
		count(*)	
	FROM 
		notice n, members m, projects p
	WHERE 
		n.prj_domain =  p.prj_domain
		AND n.notice_writer = m.memb_mail;
        
        select count(*) from notice;
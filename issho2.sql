
ALTER TABLE noticeComment
DROP CONSTRAINT FK_NOTICE_TO_NOTICECOMMENT_1;

ALTER TABLE noticeFile
DROP CONSTRAINT FK_NOTICE_TO_NOTICEFILE_1;

ALTER TABLE noticeComment
ADD CONSTRAINT noticeCommentCascade
FOREIGN KEY (notice_seq)
REFERENCES notice(notice_seq)
ON DELETE CASCADE;

ALTER TABLE noticeFile
ADD CONSTRAINT noticeFileCascade
FOREIGN KEY (notice_seq)
REFERENCES notice(notice_seq)
ON DELETE CASCADE;


//°ËÁõ¿ë sql
select * from noticecomment where notice_seq=214;
select * from notice where notice_seq =214;
delete from notice where notice_seq =214;

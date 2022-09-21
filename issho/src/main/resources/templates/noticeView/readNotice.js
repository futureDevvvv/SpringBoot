
//공지사항 삭제
function deleteNotice(notice_seq) {
	location.href = 'deleteNotice?notice_seq=' + notice_seq;
	return false;
}

function writeORupdate() {
	let mode = $('#submitComment').val();
	alert(mode);
	if(mode =='수정')
		return updateComment();
	else 
		return writeComment();		
}

function writeComment() {

	let noticeCmt_contents = $('#noticeCmt_contents').val();
	let notice_seq = $('#notice_seq').val();
	
	if (noticeCmt_contents < 5 || noticeCmt_contents > 1000) {
		alert('댓글을 5자 이상 1,000자 이하로 내용을 입력하세요.');
		return false;
	}
	
	$.ajax({
		url: 'writeComment',
		type: 'post',
		data: { notice_seq: notice_seq, noticeCmt_contents: noticeCmt_contents },
		success: function(n) {
			if(n == 0) {
				alert('저장이 실패였습니다.');
				return false;			
			}else
				return true;
		},
		error: function() {
			alert('저장이 실패였습니다.');
			return false;
		}
	});
}

function updateComment() {
	let noticeCmt_contents = $('#noticeCmt_contents').val();
	let notice_seq = $('#notice_seq').val();
	
	if (noticeCmt_contents < 5 || noticeCmt_contents > 1000) {
		alert('댓글을 5자 이상 1,000자 이하로 내용을 입력하세요.');
		return false;
	}
	
	$.ajax({
		url: 'updateComment',
		type: 'post',
		data: { noticeCmt_seq: changeCmt_seq, noticeCmt_contents: noticeCmt_contents },
		success: function(n) {
			if(n == 0) {
				alert('UP1 저장이 실패였습니다.');
				return false;			
			}else
				return true;
		},
		error: function() {
			alert('UP2 저장이 실패였습니다.');
			return false;
		}
	});
}

//전역변수로 수정 시 사용할 코멘트 번호임
let changeCmt_seq;
function changeMode(Cmt_seq){
	//변경할 댓글 번호 전역변수에 저장
	changeCmt_seq = Cmt_seq;
//	alert(changeCmt_seq);

	//변경할 장소로 인동
	location.href = '#commentForm';
	$('#submitComment').val('수정');
	$('#noticeCmt_contents').val("수정할 내용을 입력하세요");
	$('#noticeCmt_contents').select();
	
	return false;
}

function deleteComment(noticeCmt_seq) {
	
	if (confirm('삭제하시겠습니까?')==false) {
		return false;
	}
	
	$.ajax({
		url: 'deleteComment',
		type: 'get',
		data: {noticeCmt_seq: noticeCmt_seq},
		success: function(n) {
			if(n == 0) {
				alert('삭제가 실패였습니다.');
				return false;			
			}else
				return true;
		},
		error: function(e) {
			alert(e);
			return false;
		}
	});
}

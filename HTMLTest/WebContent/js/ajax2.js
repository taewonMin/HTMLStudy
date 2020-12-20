/**
 * 
 */
$(document).ready(function(){
	$("button").click(function() {
		var isTableBtn = $(this).attr("id") == "tableBtn" ? true : false;
		$.ajax({
			url: "cd_catalog.xml"
			,type: "post"
			,data: ""
			,datatype: "xml"
			,success: function(data){
				if(isTableBtn){
					makeTable(data);
				}else{
					makeText(data);
				}
			}
			,error: function(xhr){
				console.log(xhr);
			}
		});
	});
});

function makeTable(data){
	var header = ["NO", "TITLE", "ARTIST", "COUNTRY", "COMPANY", "PRICE", "YEAR"];	// 테이블 컬럼명
	var cd = data.getElementsByTagName("CD");
	
	var resultStr = "<table border='1' style='border-collapse: collapse;'>";
	// th 생성
	resultStr += "<tr>";
	for(var i in header){
		resultStr += "<th>" + header[i] + "</th>";
	}
	resultStr += "</tr>"
	
	for(var i=0; i<cd.length; i++){
		var cnt = 0;
		resultStr += "<tr>";
		for(var j=0; j<header.length; j++){
			
			var data;
			if(j==0){	// no
				data = i+1;
			}else{
				if(cd[i].children[cnt].tagName == header[j]){	// tagName이 있으면
					data = $(cd[i].children[cnt++]).text();
				} else{	// tagName이 없으면
					data = "";
				}
			}
			resultStr += "<td>" + data + "</td>";
		};
		resultStr += "</tr>";
	}
	resultStr += "</table>";
	
	$("#resultDiv").html(resultStr);
}

function makeText(data){
	var title = data.getElementsByTagName("TITLE");
	
	var resultStr = "";
	for(var i=0; i<title.length; i++){
// 				var tmp = title[i].childNodes[0].nodeValue;
		var tmp = $(title[i]).text();
		resultStr += "<p>" + tmp + "</p>";
	}
	
	$("#resultDiv").html(resultStr);
}
function showCal(strForm,strText) {
	WINwidth=160;
	WINheight=280;
	showx = event.screenX - event.offsetX - 4 - WINwidth ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;

	newWINwidth = WINwidth + 4 + 18;
	var features =
		'dialogWidth:'  + newWINwidth  + 'px;' +
		'dialogHeight:' + WINheight + 'px;' +
		'dialogLeft:'   + showx     + 'px;' +
		'dialogTop:'    + showy     + 'px;' +
		'directories:no; localtion:no; menubar:no; status=no; toolbar=no;scrollbars:no;Resizeable=no';


	aaa=window.showModalDialog("js/caltest-2.htm", " ", features );
	if (aaa != null ) {
		strTemp = eval("document." + strForm + "." + strText);
		strTemp.value=aaa;
		
	}
}


function showCalAll(strForm,strText,strText1,strText2,strText3) {
	WINwidth=160;
	WINheight=280;
	showx = event.screenX - event.offsetX - 4 - WINwidth ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;

	newWINwidth = WINwidth + 4 + 18;
	var features =
		'dialogWidth:'  + newWINwidth  + 'px;' +
		'dialogHeight:' + WINheight + 'px;' +
		'dialogLeft:'   + showx     + 'px;' +
		'dialogTop:'    + showy     + 'px;' +
		'directories:no; localtion:no; menubar:no; status=no; toolbar=no;scrollbars:no;Resizeable=no';


	aaa=window.showModalDialog("js/caltest-2.htm", " ", features );
	if (aaa != null ) {
		strTemp = eval("document." + strForm + "." + strText);
		strTemp.value=aaa;
		if(strText1!=null&&strText2!=null&&strText3!=null){
			strText1 = eval("document." + strForm + "." + strText1);
			strText2 = eval("document." + strForm + "." + strText2);
			strText3 = eval("document." + strForm + "." + strText3);
		
			strText1.value = aaa.substring(0,4);
			strText2.value = aaa.substring(5,7);
			strText3.value = aaa.substring(8,10);
		}	
	}
}

function BackToHistory() {
    history.back();
    return false;
}
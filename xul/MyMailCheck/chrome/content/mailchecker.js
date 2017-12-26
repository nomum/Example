function checkMailandAddress(){
	
	var a = document.getElementById("msgIdentity");
	Components.utils.reportError(a);
	var b = a.selectedItem;
	Components.utils.reportError(b);
	Components.utils.reportError(b.value);
	//var gMimeHeaderParser = Components.classes["@mozilla.org/messenger/headerparser;1"].getService(Components.interfaces.nsIMsgHeaderParser);
	var gMimeHeaderParser = Components.classes["@mozilla.org/messenger/headerparser;1"]
                      .createInstance(Components.interfaces.nsIMsgHeaderParser);
//	var from;
//  			try {
//  				//from = gMimeHeaderParser.reformatUnquotedAddresses(b.value,from);
//				gMimeHeaderParser.reformatUnquotedAddresses(b.value,from);
//  			} catch (ex) {
//  				from = b.value;
//  			}
	var from = parseAddress(b.value);

	Components.utils.reportError("from : " + from);
  	var toList = [];
  	var ccList = [];
  	var bccList = [];
  	this.collectAddress(toList, ccList, bccList);
	for (var i = 0 ; i < toList.length ; i++){
		Components.utils.reportError(toList[i]);
		Components.utils.reportError(parseAddress(toList[i]));
		Components.utils.reportError(parseDomain(toList[i]));
	}
	//alert("test");
	Components.utils.reportError( 'Some messages' );
	
	var subject = document.getElementById("msgSubject");
	//var win = window.openDialog("chrome://mailcheck/content/confirm.xul", "AlartWindow", "resizable,chrome,modal,titlebar,centerscreen",dspMessageMail, dspMessageAddr, window);
	var win = window.openDialog("chrome://mailcheck/content/confirm.xul", "AlartWindow", "resizable,chrome,modal,titlebar,centerscreen",toList,ccList,bccList,from,subject.value, window);
	Components.utils.reportError('return : ' + window.confirmOK);
	
	//return false;
	return window.confirmOK;
}

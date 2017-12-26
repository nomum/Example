function parseDomain(address){
	if (address == null){
		return null;
	}
    var plane = parseAddress(address);
    //@移行を取得
    var atmarkIndex = plane.indexOf("@");
    if ( atmarkIndex < 0){
        return "";
    }
    return plane.substr(atmarkIndex+1);
}
function parseAddress(address){
	if (address == null){
		return null;
	}
	var start = address.indexOf("<");
	var end = address.indexOf(">");
	if (start < 0 || end < 0){
		return address;
	}
	return address.substr(start+1 , address.length - (start+1) - (address.length - end));
	
}
function collectAddress(toList, ccList, bccList){

  	var gMimeHeaderParser = Components.classes["@mozilla.org/messenger/headerparser;1"].getService(Components.interfaces.nsIMsgHeaderParser);
  	
  	var row = 1;
  	while(true){
  		var inputField = document.getElementById("addressCol2#" + row);
  		
  		if(inputField == null){
  			break;
  		}else{
  			//row++;
  		}
  		var fieldValue = inputField.value;
  		if (fieldValue == null){
  			fieldValue = inputField.getAttribute("value");
  		}
  		
  		if (fieldValue != ""){
  			
  			var recipient = null;

  			try {
  				recipient = gMimeHeaderParser.reformatUnquotedAddresses(fieldValue);
  			} catch (ex) {
  				recipient = fieldValue;
  			}

  			var recipientType = "";
  			var popupElement = document.getElementById("addressCol1#" + row++);
  			if(popupElement != null){
  				recipientType = popupElement.selectedItem.getAttribute("value");
  			}

//            window.confirm(recipientType + ":" + recipient);

  			switch (recipientType){
  			case "addr_to":
  				toList.push(recipient);
  				break;
  			case "addr_cc":
  				ccList.push(recipient);
  				break;
  			case "addr_bcc":
  				bccList.push(recipient);
  				break;
  			default:
  				toList.push(recipient);
  				break;
  			}
  		}else{
  		   row++;
  		}
  	}
}
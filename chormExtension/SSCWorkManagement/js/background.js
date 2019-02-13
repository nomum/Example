// ローカルストレージを取得するメッセージを受け付ける
chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
  console.log("[start]back ground page action")
  if (request.action === "getLocalStorage"){
    console.log("request.action === getLocalStorage")
    console.log(localStorage.key)
    sendResponse(localStorage);
    }
  });
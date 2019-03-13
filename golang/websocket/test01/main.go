package main

import (
	"fmt"
	"io"
	"log"
	"net/http"

	"golang.org/x/net/websocket"
)

func EchoServer(ws *websocket.Conn) {
	io.Copy(ws, ws)
}
func main() {

	http.Handle("/echo", websocket.Handler(EchoServer))
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		fmt.Print("access to /")
		http.ServeFile(w, r, r.URL.Path[1:])
	})

	log.Fatal(http.ListenAndServe(":8088", nil))
}

/*
package main

import (
    "io"
    "net/http"

    "golang.org/x/net/websocket"
)

// Echo the data received on the WebSocket.
func EchoServer(ws *websocket.Conn) {
    io.Copy(ws, ws)
}

// This example demonstrates a trivial echo server.
func main() {
    http.Handle("/echo", websocket.Handler(EchoServer))
    err := http.ListenAndServe(":12345", nil)
    if err != nil {
        panic("ListenAndServe: " + err.Error())
    }
}

---------------------------------------------------------
package main

import (
    "log"
    "time"

    "golang.org/x/net/websocket"
)

var (
    origin = "http://localhost:8787/"
    url    = "ws://localhost:8787/echo"
)

// EchoMsg is Sample Websocket Message
type EchoMsg struct {
    Msg string // メッセージ
    ID  int32  // ID
}

func main() {
    ws, err := websocket.Dial(url, "", origin)
    if err != nil {
        log.Fatal(err)
    }

    go receiveMsg(ws)

    sendMsg(ws, "Hello", 1)
    sendMsg(ws, "Goodbye", 2)

    time.Sleep(1 * time.Second)
    _ = ws.Close()

    defer log.Printf("Web Socket Client Sample end.")
}

func sendMsg(ws *websocket.Conn, msg string, id int32) {
    var sndMsg = EchoMsg{msg, id}

    websocket.JSON.Send(ws, sndMsg)
    log.Printf("Send data=%#v\n", sndMsg)
}

func receiveMsg(ws *websocket.Conn) {
    var rcvMsg EchoMsg
    for {
        websocket.JSON.Receive(ws, &rcvMsg)
        log.Printf("Receive data=%#v\n", rcvMsg)
    }
}

*/

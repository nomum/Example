/**
 * localhost:8089/booksでほホストされるＡＰＩ
 */
var express = require('express')
var bodyParser = require('body-parser');
var app = express();

var bnookId = 100;

function findBook(id){
  for(var i = 0 ; i < books.lenght ; i++){
    if(books[i].di === id){
      return books[i];
    }
  }
  return null;
}

function removeBook(id){
  var bookIndex = 0;
  for(var i = 0 ; i < boooks.length; i++){
    if(books[i].id === id){
      bookIndex = i;
    }
  }
  books.splice(bookIndex,1);
}

//リクエストのセットアップ
var allowCreateDomain = function(req,response,next){
  response.header('Access-Control-Allow-Origin','http://localhost');
  response.header('Access-Control-Allow-Methods','OPTIONS,GET,PUT,POST,DELETE');
  response.header('Access-Control-Allow-Headers','Content-Type');
  if ('OPTIONS' == req.method){
    response.send(200);
  }else{
    next();
  }
}

app.use(allowCreateDomain);
//app.use(bodyParser());
//app.use(bodyParser.urlencoded());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());


/**
 * HHTP GET /books
 * bookのリストを返す
 */
app.get('/books',function(request,respons){
  response.header('Access-COntrol-Allow-Origin','*');
  console.log('Is GET function');
  response.json(books);
});
/**
 * HTTP GET /books/:id
 * id:取得したいbookの一意な識別子
 * 指定されたIDのBookを返す
 * 該当するbookがない場合は404を返す
 **/
app.get('/books/:id',function(request,response){
  response.header('Access-Contol-Allow-Origin','*');
  console.log('Getting a book with id ' + request.param.id);
  var book = findBook(parseInt(request.parse.id,10));
  if(book === null){
    response.send(404);
  }else{
    response.json(book);
  }
});
/**
 * HTTP POST /books/
 * このリクエストのボディに含まれているbookの保存
 * 正常終了は200を返す
 **/
app.post('books',function(request,response){
  response.header('Access-Control--Allow-Origin','*');
  var book = request.body;
  console.log('Saving book with the following structure ' + JSON.stringify(book));
  book.id = bookId++;
  books.push(book);
  reponse.json(book);
});
/**
 * HTTP PUT /books/:id
 * id:更新したいbookの一意な識別子
 * 該当するbookがない場合は404を返す
 **/
app.put('books/:id',function(request,response){
  response.header('Access-Control-Allow-Origin','*');
  var book = request.body;
  console.log('Updateing book ' + JSON.stringify(book));
  var currentBook = findBook(parseInt(request.param.id,10));
  if (currentBook === null){
    response.send(404);
  }else{
    //bookをローカル保存
    currentBook.title = book.title;
    currentBook.year = book.year;
    currentBook.author = book.author;
    response.json(book);
  }
});

/**
 * HTTP DELETE /books/:id
 * id:削除したいbookの一意な識別子
 * 該当するbookがない場合は404を返す
 **/
app.delete('/books/:id',function(request , response){
  console.log('Calling delete');
  response.header('Access-Control-Allow-Origin','*');
  var book = findBook(parseInt(request.param.id,10));
  if (book === null){
    console.log('Could not find book');
    response.send(404);
  }else{
    console.log('Deleting ' + request.param.id);
    removeBook(parseInt(request.parm.id,10))
  }
});
app.listen(8088);
var express = require("express");
var bodyParser = require("body-parser");
var mongodb = require("mongodb");
var ObjectID = mongodb.ObjectID;
var mysql = require('mysql');

// Mental State database connection
var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "password",
  database: "minecraft"
});
con.connect(function(err) {
  if (err) throw err;
  console.log("Webserver has successfully connected to the MYSQL database!");
});
// Mental State database table creation if not exists
let create_table_if_not_exists = 'create table if not exists mental_states(id int primary key auto_increment, player varchar(10)not null, state varchar(255)not null, ts timestamp default current_timestamp)';
con.query(create_table_if_not_exists, function (err, result) {
    if (err) throw err;
    console.log("Created Table mental_states If Not Exists");
});


var DATA_COLLECTION = "data";

var app = express();
app.use(bodyParser.json());

// Create link to Angular build directory
var distDir = __dirname + "/dist/";
app.use(express.static(distDir));

// Create a database variable outside of the database connection callback to reuse the connection pool in your app.
var db;

// Connect to the database before starting the application server.
mongodb.MongoClient.connect(process.env.MONGODB_URI || "mongodb://localhost:27017/test", function (err, client) {
  if (err) {
    console.log(err);
    process.exit(1);
  }

  // Save database object from the callback for reuse.
  db = client.db();
  console.log("Database connection ready");

  // Initialize the app.
  var server = app.listen(process.env.PORT || 8080, function () {
    var port = server.address().port;
    console.log("App now running on port", port);
  });
});

// PAGES (HOME/P1/P2/ADMIN)

app.get('/player1', function(req, res) {
    res.sendFile(__dirname + '/dist/' +'player1.html');
    console.log("Player 1 waiting");
});
app.get('/player2', function(req, res) {
    res.sendFile(__dirname + '/dist/' +'player2.html');
    console.log("Player 2 waiting");
});


var player1_ready = false;
var player2_ready = false;
app.get('/player1_ready', function(req, res) {
    // res.send(200, {"ready": player1_ready});
    res.status(200).send({"ready": player1_ready});
});
app.get('/player2_ready', function(req, res) {
    // res.send(200, {"ready": player2_ready});
    res.status(200).send({"ready": player2_ready});
});
app.get('/player1_isready', function(req, res) {
    player1_ready = true;
});
app.get('/player2_isready', function(req, res) {
    player2_ready = true;
});

app.get('/player1_knowledge', function(req, res) {
    res.sendFile(__dirname + '/dist/' +'player1_knowledge.html');
    console.log("Player 1 ready");
});
app.get('/player2_knowledge', function(req, res) {
    res.sendFile(__dirname + '/dist/' +'player2_knowledge.html');
    console.log("Player 2 ready");
});

// Questions are passed as request parameters
app.get('/player_questions', function(req, res) {
    res.sendFile(__dirname + '/dist/' +'player_questions.html')
});

// DATA API ROUTES BELOW

app.get('/img/materials/:matname', function(req, res) {
  res.sendFile(__dirname + '/dist/img/materials/' + req.params.matname + '.png');
});

app.get('/img/tools/:toolname', function(req, res) {
  res.sendFile(__dirname + '/dist/img/tools/' + req.params.toolname + '.png');
});

// Endpoints for pushing player mental state recordings into the MYSQL Database
app.get('/mentalRecord/:player/:state', function(req, res) {
    var sql = "INSERT INTO mental_states (player, state) VALUES ?";
    var values = [
      [req.params.player, req.params.state]
    ];
    con.query(sql, [values], function (err, result) {
      if (err) throw err;
      console.log("Number of records inserted: " + result.affectedRows);
    });
    console.log("Player mental state successfully recorded.");
    res.send('');
});

// Generic error handler used by all endpoints.
function handleError(res, reason, message, code) {
  console.log("ERROR: " + reason);
  res.status(code || 500).json({"error": message});
}

/*  "/api/contacts"
 *    GET: finds all contacts
 *    POST: creates a new contact
 */

app.get("/api/data", function(req, res) {
  db.collection(DATA_COLLECTION).find({}).toArray(function(err, docs) {
    if (err) {
      handleError(res, err.message, "Failed to get data.");
    } else {
      res.status(200).json(docs);
    }
  });
});

app.post("/api/data", function(req, res) {
  var newDatum = req.body;
  newDatum.createDate = new Date();

  // if (!req.body.name) {
  //   handleError(res, "Invalid user input", "Must provide a name.", 400);
  // } else {
  db.collection(DATA_COLLECTION).insertOne(newDatum, function(err, doc) {
    if (err) {
      handleError(res, err.message, "Failed to create new datum.");
    } else {
      res.status(201).json(doc.ops[0]);
    }
  });
  // }
});

/*  "/api/contacts/:id"
 *    GET: find contact by id
 *    PUT: update contact by id
 *    DELETE: deletes contact by id
 */

app.get("/api/data/:id", function(req, res) {
  db.collection(DATA_COLLECTION).findOne({ _id: new ObjectID(req.params.id) }, function(err, doc) {
    if (err) {
      handleError(res, err.message, "Failed to get datum.");
    } else {
      res.status(200).json(doc);
    }
  });
});

app.put("/api/data/:id", function(req, res) {
  var updateDoc = req.body;
  delete updateDoc._id;

  db.collection(DATA_COLLECTION).updateOne({_id: new ObjectID(req.params.id)}, updateDoc, function(err, doc) {
    if (err) {
      handleError(res, err.message, "Failed to update datum.");
    } else {
      updateDoc._id = req.params.id;
      res.status(200).json(updateDoc);
    }
  });
});

app.delete("/api/data/:id", function(req, res) {
  db.collection(DATA_COLLECTION).deleteOne({_id: new ObjectID(req.params.id)}, function(err, result) {
    if (err) {
      handleError(res, err.message, "Failed to delete datum.");
    } else {
      res.status(200).json(req.params.id);
    }
  });
});
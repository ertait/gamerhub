<?php
include "constants.php";
include "pass.php";
include "Database.php";
//if ($_GET) {
$dbUserName = "ispizize_reader";
$whichPass = "r"; //flag for which one to use.
$dbName = "ISPIZIZE_GameHub";
$thisDatabaseReader = new Database($dbUserName, $whichPass, $dbName);
$dbUserName = "ispizize_writer";
$whichPass = "w"; //flag for which one to use.
//$dbName = DATABASE_NAME;
$dbName = "ISPIZIZE_GameHub";
$thisDatabaseWriter = new Database($dbUserName, $whichPass, $dbName);
 //get the user ID from the database since they are already logged in
 //for now I am just hard coding it for ease
$threadName=$_GET["threadTitle"];
//$threadName="If only";
$userID=$_GET["userId"];
//    $userId="8";
$gameID=$_GET["gameId"];
//$threadNumQuery = "select pmkPostId from tblCallofDuty4Threads where txtThreadName=?";
//$params = array($threadName);
//$results = $thisDatabaseReader->select($threadNumQuery,$params,1,0,0,0,false,false);
//$threadNum="";
//if (!empty($results) && $results[0] != " ") {
//            foreach ($results as $val) {
//
//                $threadNum = $threadNum. $val[0];
//            }
//        }
// $threadNum = "";
 
 //get the numThreadNum for the database - this is the thread number, so every post from this thread will have the same txtThreadNum
 //for now I am hard coding it for ease
// $threadNum = 1;
 //get the fnkGameId from the database - the game Id is the primary key given to the game in the videogames table
 //hard coding for now
// $gameId = 1;
// if (!empty($results)){

    
//    echo json_encode($result);
$query = "select txtThread, fldUsername from tblCallofDuty4Threads join tblUserProfile on fnkUserId=pmkUserId where txtThreadName = ?";
$parameters = array($threadName);
//execute the query on the database object
$threads = $thisDatabaseReader->select($query, $parameters, 1, 0, 0, 0, false, false);
// }


// $threads.append($result);
 echo json_encode($threads);
//if (is_array($threads)){
//        foreach ($threads as $c){
//            print "<p> User ID: " .$c['fnkUserId']. " -  Comment: " .$c['txtThread']. "</p>" ;
//        }
//    }
//if (isset($_GET['frmComment'])){
//    $comment = $_GET['frmComment'];
//}else{
//    $comment = "";
//}
//if (isset($_GET["submit"])){
//    //inser into the thread table
//    $data = array($gameId, $threadNum, $comment, $userID);
//    $query = "insert into tblCallofDuty4Threads(fnkGameId, numThreadNum, txtThread, fnkUserId) VALUES (?, ?, ?,?)";
//    $results = $thisDatabaseWriter->insert($query, $data, 0,0,0,0,false,false);
//    header('Location: https://ispizize.w3.uvm.edu/GameHub/DBconnect/gameforum.php');
    //insert into the table inbetween user and thread
//}
?>

<!--<article id="main">

    <form action="gameforum.php"
        method="get"
        id="frmgameforum">

        <fieldset class="wrapper">
                <legend>Enter Your Comment</legend>
                <textarea name="frmComment" id="frmComment" cols="50" rows="10"
                value="<?php //print $comment; ?>"></textarea>
        </fieldset>

        <fieldset class="buttons">
                <legend></legend>
                <input type="submit" id="submit" name="submit" value="submit" tabindex="9" class="button">
            </fieldset>  ends buttons 
    </form>




</article>
</body>
</html>-->
<?php

include "constants.php";
include "pass.php";
include "Database.php";



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

 $userID = 1;

 //get the numThreadNum for the database - this is the thread number, so every post from this thread will have the same txtThreadNum
 //for now I am hard coding it for ease

 $threadNum = 1;

 //get the fnkGameId from the database - the game Id is the primary key given to the game in the videogames table
 //hard coding for now

 $gameId = 1;


?>


<?php
//GET ALL THREADS A USER IS SUBSCRIBED TO

$userID = 1;
$query = "select fnkThreadNum from tblUserSubscriptions where fnkUserId = ?";
$parameters = array($userID);
//execute the query on the database object
$threadnums = $thisDatabaseReader->select($query, $parameters, 1, 0, 0, 0, false, false);


//$queryNums is the fnkThreadNums from tblUserSubscriptions
//I push all of these numbers into an array call $queryNums
$queryNums = array();
foreach ($threadnums as $c){
        
        array_push($queryNums, $c[0]);
        }

//$finalThreadNames is an array with all of the thread names at the end
$finalThreadNames = array();
//i loop through the query nums and push each thread onto the finalthreadnames array
for($i=0; $i<count($queryNums); ++$i){
$query = "select txtThreadName from tblCallofDuty4Threads where numThreadNum = ?";
$parameters = array($queryNums[$i]);
//execute the query on the database object
$threadnames = $thisDatabaseReader->select($query, $parameters, 1, 0, 0, 0, false, false);
//pushes each thread onto the array
foreach ($threadnames as $c){
        array_push($finalThreadNames, $c[0]);
        }
    
}
//prints all the thread nums
foreach($finalThreadNames as $t){
	echo $t;
	echo "<br>";
	}

?>
</body>
</html>
<?php

include "constants.php";
include "pass.php";
include "Database.php";


$dbUserName = "ispizize_writer";
$whichPass = "w"; //flag for which one to use.
//$dbName = DATABASE_NAME;
$dbName = "ISPIZIZE_GameHub";
$thisDatabaseWriter = new Database($dbUserName, $whichPass, $dbName);



	$userID = 5;
	$gameId = 5;
	//inserts them into the users subscriptions table which is what threads they are subscribed to (NEED GAMEID AND USERID)
	$data = array($userID, $gameId);
    $query = "insert into tblUserSubscriptions(fnkuserId, fnkThreadNum) VALUES (?, ?)";
    $results = $thisDatabaseWriter->insert($query, $data, 0,0,0,0,false,false);
    
?>
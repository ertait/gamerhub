<?php

include "constants.php";
include "pass.php";
include "Database.php";


$dbUserName = "ispizize_writer";
$whichPass = "w"; //flag for which one to use.
//$dbName = DATABASE_NAME;
$dbName = "ISPIZIZE_GameHub";
$thisDatabaseWriter = new Database($dbUserName, $whichPass, $dbName);



	$userID = $_POST["userId"];
//        $userID ="53";
//	$gameId = 5;
        $threadName=$_POST["threadName"];
//        $threadName = "Civ Hype";
	//inserts them into the users subscriptions table which is what threads they are subscribed to (NEED GAMEID AND USERID)
	$data = array($userID, $threadName);
    $query = "insert into tblUserSubscriptions(fnkUserId, txtThreadTitle) VALUES (?, ?)";
    $results = $thisDatabaseWriter->insert($query, $data, 0,0,0,0,false,false);
    header( 'HTTP/1.1 201: Resource Created' );
    
?>
<?php

    require_once "constants.php";
    require_once "pass.php";
    require_once "Database.php";



    //create the database writerObject
    //$dbUserName = get_current_user() . '_writer';
    $dbUserName = "ispizize_writer";
    $whichPass = "a"; //flag for which one to use.
    //$dbName = DATABASE_NAME;
    $dbName = "ISPIZIZE_GameHub";
    $thisDatabaseAdmin = new Database($dbUserName, $whichPass, $dbName);
    
    
        $gameid = $_POST["gameId"];
    $userid = $_POST["userId"];
    $title = $_POST["title"];
//    $text = $_POST["text"];
//        $gameid = "2";
//        $userid= "8";
//        $title="New Civ Thread";
	//create the database writerObject
	//$dbUserName = get_current_user() . '_writer';
        $dbUserName = "ispizize_writer";
	$whichPass = "w"; //flag for which one to use.
	//$dbName = DATABASE_NAME;
        $dbName = "ISPIZIZE_GameHub";
	$thisDatabaseWriter = new Database($dbUserName, $whichPass, $dbName);
	
	//build and execute the query
   
	$query = "insert into tblCallofDuty4Threads(fnkGameId, fnkUserId, txtThreadName) VALUES (?, ?, ?)";
	$parameters = array($gameid, $userid, $title);
	$result = $thisDatabaseWriter->insert($query, $parameters, 0,0,0,0,false,false);

//$query = "CREATE TABLE IF NOT EXISTS tblDoomThreads (
//    fnkGameId int(11) NOT NULL,
//    pmkPostId int(11) NOT NULL AUTO_INCREMENT
//    numThreadNum int(11) NOT NULL,
//    txtThread text NOT NULL,
//    fnkUserId int(11) NOT NULL,
//    txtThreadName varchar(500) NOT NULL,
//    PRIMARY KEY (pmkPostId)
//    )"

header( 'HTTP/1.1 201: Resource Created' );
?>
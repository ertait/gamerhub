<?php

    require_once "constants.php";
    require_once "pass.php";
    require_once "Database.php";



    //create the database writerObject
    //$dbUserName = get_current_user() . '_writer';
    $dbUserName = "ispizize_reader";
    $whichPass = "r"; //flag for which one to use.
    //$dbName = DATABASE_NAME;
    $dbName = "ISPIZIZE_GameHub";
    $thisDatabaseReader = new Database($dbUserName, $whichPass, $dbName);
    
    
//        
//    $gameId="none";
//    $threadName="If only";
//    $userId = "8";
//    $text = "testing null gameId";
    $threadName = $_POST["threadName"];
        $gameId=$_POST["gameId"];
    $userId = $_POST["userId"];
//    $title = $_POST["title"];
    $text=$_POST["text"];
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
        $getGameId ="";
	if ($gameId == "none"){
            $query = "select fnkGameId from tblCallofDuty4Threads where txtThreadName = ?";
            $params = array($threadName);
            $results = $thisDatabaseReader->select($query, $params, 1, 0, 0, 0, false, false);
        if (!empty($results) && $results[0] != " ") {
        foreach ($results as $val) {

            $getGameId = $gameId . $val[0];
        }
        
    }
        $gameId = $getGameId;
        }
	//build and execute the query
        
	$query = "insert into tblCallofDuty4Threads(fnkGameId, fnkUserId, txtThreadName, txtThread) VALUES (?, ?, ?,?)";
//        echo $query;
	$parameters = array($gameId, $userId, $threadName, $text);
	$result = $thisDatabaseWriter->insert($query, $parameters, 0,0,0,0,false,false);
        


header( 'HTTP/1.1 201: Resource Created' );
?>
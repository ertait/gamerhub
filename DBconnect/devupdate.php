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
    
    $dbUserName = "ispizize_writer";
	$whichPass = "w"; //flag for which one to use.
	//$dbName = DATABASE_NAME;
        $dbName = "ISPIZIZE_GameHub";
	$thisDatabaseWriter = new Database($dbUserName, $whichPass, $dbName);
    $userID = $_POST["userID"];
    $gameName = $_GET["GameId"];
//        $userID = "53";
//        $gameName="World of Warcraft";

    $gameQuery = "select pmkGameId from tblVideoGame where fldTitle =?";
    $params = array($gameName);
    $results = $thisDatabaseReader->select($gameQuery, $params, 1,0,0,0,false,false);
    $gamestring="";
     if (!empty($results) && $results[0] != " ") {
        foreach ($results as $val) {

            $gamestring = $gamestring . $val[0];
        }
    }
    $gameID=$gamestring;
    $query = "select pmkUserId from tblUserProfile where fldUsername =?";
    $params = array($userID);
    $results = $thisDatabaseReader->select($gameQuery, $params, 1,0,0,0,false,false);
    $user="";
     if (!empty($results) && $results[0] != " ") {
        foreach ($results as $val) {

            $user = $user . $val[0];
        }
    }
    echo $gamestring;
    echo $user;
	//updates the user profile to set their devstatus to 1
	$query = "UPDATE tblUserProfile set devStatus=1 WHERE pmkUserId = ? ";
	$data = array($userID); 
	$results = $thisDatabaseWriter->update($query, $data); 
	
	//inserts them into the developers users table (NEED GAMEID AND USERID)
	$data = array($user, $gameID, "1");
    $query = "insert into tblDevelopersUser(fnkUserId, fnkGameId, intStatus) VALUES (?, ?, ?)";
    $results = $thisDatabaseWriter->insert($query, $data, 0,0,0,0,false,false);
    
?>
<?php
	//updates the user profile to set their devstatus to 1
	$query = "UPDATE tblUserProfile set devStatus=1 WHERE pmkUserId = ? ";
	$data = array($userID); 
	$results = $thisDatabaseWriter->update($query, $data); 
	
	//inserts them into the developers users table (NEED GAMEID AND USERID)
	$data = array($userID, $gameId, "1");
    $query = "insert into tblDevelopersUser(fnkuserId, fnkGameId, intStatus) VALUES (?, ?, ?)";
    $results = $thisDatabaseWriter->insert($query, $data, 0,0,0,0,false,false);
    
?>
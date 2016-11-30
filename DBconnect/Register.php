<?php

  require_once "constants.php";
  require_once "pass.php";
  require_once "Database.php";

        $name = $_POST["name"];
    $username = $_POST["username"];
    $password = $_POST["password"];
	//create the database writerObject
	//$dbUserName = get_current_user() . '_writer';
        $dbUserName = "ispizize_writer";
	$whichPass = "w"; //flag for which one to use.
	//$dbName = DATABASE_NAME;
        $dbName = "ISPIZIZE_GameHub";
	$thisDatabaseWriter = new Database($dbUserName, $whichPass, $dbName);
	
	//build and execute the query
   
	$query = "insert into tblUserProfile(fldFirstName, fldUsername, fldPassword) VALUES (?, ?, ?)";
	$parameters = array($name, $username,$password);
	$result = $thisDatabaseWriter->insert($query, $parameters, 0,0,0,0,false,false);
        
	header( 'HTTP/1.1 201: Resource Created' );	

?>
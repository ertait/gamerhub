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

$query = "CREATE TABLE IF NOT EXISTS tblDoomThreads (
    fnkGameId int(11) NOT NULL,
    pmkPostId int(11) NOT NULL AUTO_INCREMENT
    numThreadNum int(11) NOT NULL,
    txtThread text NOT NULL,
    fnkUserId int(11) NOT NULL,
    txtThreadName varchar(500) NOT NULL,
    PRIMARY KEY (pmkPostId)
    )"


?>
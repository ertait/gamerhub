<?php

require_once "constants.php";
require_once "pass.php";
require_once "Database.php";
if ($_GET) {
    $name = $_GET["username"];
    $pass = $_GET["password"];

    $dbUserName = "ispizize_reader";
    $whichPass = "r"; //flag for which one to use.
    $dbName = "ISPIZIZE_GameHub";
    $thisDatabaseReader = new Database($dbUserName, $whichPass, $dbName);

    //build our query
    $query = "select fldPassword from tblUserProfile where fldUsername = ?";
    $parameters = array($name);
    //execute the query on the database object
    $results = $thisDatabaseReader->select($query, $parameters, 1, 0, 0, 0, false, false);

    //extract the results and write them to a string for packaging to JSON
    $resultString = "";

    if (!empty($results) && $results[0] != " ") {
        foreach ($results as $val) {

            $resultString = $resultString . $val[0];
        }
    }

    $r = (string) $results;
    echo json_encode($results);
}
?>
<?php

require_once "constants.php";
require_once "pass.php";
require_once "Database.php";
//if ($_GET) {
    $name = $_GET["userId"];
//        $name="8";
    //$pass = $_GET["password"];
//    $name = "First Person Shooter";
    $dbUserName = "ispizize_reader";
    $whichPass = "r"; //flag for which one to use.
    $dbName = "ISPIZIZE_GameHub";
    $thisDatabaseReader = new Database($dbUserName, $whichPass, $dbName);

    //build our query
   $query = "select txtThreadTitle from tblUserSubscriptions where fnkUserId = ?";
//    $query = "select fldTitle from tblCallofDuty4Threads join tblVideoGame on fnkGameId=pmkGameId where fnkUserId = ?";
$parameters = array($name);
//execute the query on the database object
$results = $thisDatabaseReader->select($query, $parameters, 1, 0, 0, 0, false, false);
// }
 
// echo json_encode($threads);

    //extract the results and write them to a string for packaging to JSON
    $resultString = "";

    if (!empty($results) && $results[0] != " ") {
        foreach ($results as $val) {

            $resultString = $resultString . $val[0];
        }
    }
//    echo $resultString;
    $query = "select distinct fnkGameId from tblCallofDuty4Threads where txtThreadName =?";
    $parameters = array($resultString);
    $results = $thisDatabaseReader->select($query, $parameters, 1, 0, 0, 0, false, false);
    $gamestring = "";

    if (!empty($results) && $results[0] != " ") {
        foreach ($results as $val) {

            $gamestring = $gamestring . $val[0];
        }
    }
//    echo $gamestring;
$query = "select fldTitle from tblVideoGame where pmkGameId =?";
$params = array($gamestring);
$result = $thisDatabaseReader->select($query, $params, 1, 0, 0, 0, false, false);
    
    echo json_encode($result);
//}
?>
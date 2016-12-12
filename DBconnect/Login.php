<?php

require_once "constants.php";
require_once "pass.php";
require_once "Database.php";
//if ($_GET) {
//    $name = $_GET["username"];
//    $pass = $_GET["password"];
    $name="ertait";
    
    $dbUserName = "ispizize_reader";
    $whichPass = "r"; //flag for which one to use.
    $dbName = "ISPIZIZE_GameHub";
    $thisDatabaseReader = new Database($dbUserName, $whichPass, $dbName);
//    $devstatus="select devStatus from tblUserProfile where fldUsername =?";
//    $params = array($name);
//    $results = $thisDatabaseReader->select($devstatus,$params,1,0,0,0,false,false);
//        $devID = "";
//
//    if (!empty($results) && $results[0] != " ") {
//        foreach ($results as $val) {
//
//            $devID = $devID . $val[0];
//        }
//    }
//    echo $devID;
//    if ($devID ==1){
//         $usrQuery="select pmkUserID from tblUserProfile where fldUsername =?";
//        $params = array($name);
//        $results = $thisDatabaseReader->select($usrQuery,$params,1,0,0,0,false,false);
//            $usrID = "";
//
//        if (!empty($results) && $results[0] != " ") {
//            foreach ($results as $val) {
//
//                $usrID = $usrID . $val[0];
//            }
//        }
//        echo $usrID;
//        $devAdmin = "select fnkGameID from tblDeveloperAdmins where fnkUserId = ?";
//        $params = array($usrId);
//        $results = $thisDatabaseReader->select($usrQuery,$params,1,0,0,0,false,false);
//            $devAd = "";
//
//        if (!empty($results) && $results[0] != " ") {
//            foreach ($results as $val) {
//
//                $devAd  = $devAd  . $val[0];
//            }
//        }
//        echo $devAd ;
//        if ($devAd != null){
//            //  if the developer admin is not null they are an admin
//        }else{
//            //otherwise they are a developer user. 
//        }
//        //  ADD WHETHER THEY ARE A DEVELOEPER ADMIN OR USER AND USERID TO PASSWORD ARRAY AND ENCODE INTO A JSON OBJECT. 
//
//
////        //        $devUser = "select fnkGameID from tblDevelopersUser where fnkUserID = ?";
////        $params = array($usrId);
////        $results = $thisDatabaseReader->select($usrQuery,$params,1,0,0,0,false,false);
////            $devUser = "";
////
////        if (!empty($results) && $results[0] != " ") {
////            foreach ($results as $val) {
////
////                $devUser  = $devUser  . $val[0];
////            }
////        }
////        echo $devUser ;
//    }
//   
//    $devquery = "select fnkUserID from tblDeveloperAdmin"
//    build our query
    $query = "select fldPassword,pmkUserID from tblUserProfile where fldUsername = ?";
    $parameters = array($name);
    //execute the query on the database object
    $results = $thisDatabaseReader->select($query, $parameters, 1, 0, 0, 0, false, false);
    $resultString = "";

    if (!empty($results) && $results[0] != " ") {
        foreach ($results as $val) {

            $resultString = $resultString . $val[0];
        }
    }
    $result[0]["devStatus"]="A";
    print_r($results);

    //$r = (string) $results;
    //echo json_encode($results)
    //extract the results and write them to a string for packaging to JSON

//    $r = (string) $results;

//    echo json_encode($results);
//}
?>
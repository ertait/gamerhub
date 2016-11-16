<?php
include "constants.php";
/*
Include constants.php which has things like the database name and filepaths
pass.php has the passwords required to open the database and should be php included at the top of all files


database.php is written by Bob and takes some time to get used to.  I will put some sample queries for select, insert, update, and create new below


This will select the title of all games where fld genre is = to whatever is in our data array (first person shooter in this case).
The $query is the query you are writing, array($genre) is the data that you are filling the question mark with (MUST BE IN ARRAY FORM)
the first number is how many where clauses
the second number is how many conditions (AND, OR)
the third and fourth number arnt really used but are explained in database.php, and the false false is explained there also
this will save everything into the variable games which will be an array


$genre = "First Person Shooter";
$query = "SELECT fldTitle FROM tblClasses
WHERE fldGenre = ? ";
$games = $thisDatabaseReader->select($query, "", 1, 0, 0, false, false);





$data is the array of data that you are inserting into the fields that you set as ?
$query is the insert statement and you need to set all the fields you are inserting in to = ?.

$data = array("Call of Duty", "FPS", "1");
$query = 'INSERT INTO tblVideoGame SET fldTitle = ?, fldGenre = ?, pmkGameId = ?;
$results = $thisDatabaseWriter->insert($query, $data);



*/
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Base PHP connection File</title>
        <meta charset="utf-8">
        <meta name="author" content="GameHub Database Connection">
        <meta name="description" content="Database test connection">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="//html5shim.googlecode.com/sin/trunk/html5.js"></script>
        <![endif]-->

        <link rel="stylesheet" href="css/base.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/custom.css" type="text/css" media="screen">

        <?php


        // inlcude all libraries.

        print "<!-- require Database.php -->";
        require_once('Database.php');

        // Set up database connection

        print "<!-- make Database connections -->";
        $dbName = DATABASE_NAME;

        $dbUserName = get_current_user() . '_reader';
        $whichPass = "r"; //flag for which one to use.
        //use this for select statements
        $thisDatabaseReader = new Database($dbUserName, $whichPass, $dbName);
        
        $dbUserName = get_current_user() . '_writer';
        $whichPass = "w";
        //use this for insert and update statements
        $thisDatabaseWriter = new Database($dbUserName, $whichPass, $dbName);
        
        $dbUserName = get_current_user() . '_admin';
        $whichPass = "a";
        //use this for creating new tables
        $thisDatabaseAdmin = new Database($dbUserName, $whichPass, $dbName);
        ?>	

    </head>

    <!-- **********************     Body section      ********************** -->
    <?php
    print '<body id="' . $PATH_PARTS['filename'] . '">';
    include "header.php";
    include "nav.php";
    ?>
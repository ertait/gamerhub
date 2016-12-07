<?php
// This page is only viewed by the admin, mail is sent to sponsor or student
include ("../top.php");
// Note top.php sanatizes _GET
//############################################################################
// Section 1
// Initialize variables

$message = "";
$messageA = "";
$messageB = "";
$to = "";

//############################################################################
// Section 2
// Process request
if (isset($_GET["q"])) {
    $keyId = (int) $_GET["q"];

    $data = array($keyId);

    $sql = "SELECT fldEmail FROM tblRegister WHERE pmkRegisterId = ?";
    $user = $thisDatabaseReader->select($sql, $data, 1);

    $sql = "UPDATE tblRegister set fldApproved = 1 WHERE pmkRegisterId = ?";
    $approved = $thisDatabaseWriter->update($sql, $data, 1);

    if ($approved) {
        $to = $user[0]["fldEmail"];
        $subject = "PLH Registration Approved: " . $todaysDate;

        $UserMessage = "<p>Thank you for being part of the Peace Love and Happyness camp. Your registration has been approved. We will be in contact with you shortly.</p>";
        $UserMessage .= '<p><strong>Again thank you,</p>';
        $UserMessage .= '<p><strong>PLH Camp Staff</strong></p>';
    } else {
        $UserMessage = "<p>I am sorry but this registration cannot be approved at this time. Please call (802) 656 - 1234 for help in resolving this matter.</p>";
    }

//############################################################################
// Section 3
// Inform user
    print '    <section id="main">';
    if ($approved) {



        print '            <h1>Approved Registration</h1>';
    } else {
        print '            <h1>Approval Failed</h1>';
        $to = ADMIN_EMAIL;
    }


    $cc = "";
    $bcc = ADMIN_EMAIL;
    $from = "PLH Staff <plh@yoursite.com>";

    $mailed = sendMail($to, $cc, $bcc, $from, $subject, $UserMessage);
    print "<p>";
    if (!$mailed) {
        print "NOT ";
    }

    print "Mailed to: " . $to . "</p>";
    print $UserMessage;
} else {
    print "<p>Page is not available at this time, please contact us for help.</p>";
}
?>
</section>
</body>
</html>
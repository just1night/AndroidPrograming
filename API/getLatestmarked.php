<?php

require_once 'bookmark.php';


$idacc = "1";


if (isset($_POST['idacc'])) {

    $idacc = $_POST['idacc'];
}



$bookmark = new BookMark();

$response = array();
$response = json_encode($bookmark->latestmarked($idacc));
echo $response;

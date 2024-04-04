<?php
include_once 'config.php';
// require_once 'user.php';
// require_once 'novel.php';
// require_once 'message.php';

// $novel = new Novel();
// $novel->__construct();
// $response = array();
// $response = json_encode($novel->getTop5());

// require_once 'rate.php';
// $rate = new RateNV;
// $rate->__construct();

// $idacc = 1;

// $idnovel = 1;

// $rateing = 3;

// $username = "";
// if (isset($_POST['username'])) {

//     $username = $_POST['username'];
// }

// $user = new User();
// $user->__construct();
// $response = array();
// $response = json_encode($user->getuserinfor("huy"));
// echo $response;


// $idacc = "";

// $idnovel = "";

// $content = "";


// if (isset($_POST['idacc'])) {

//     $idacc = $_POST['idacc'];
// }

// if (isset($_POST['idnovel'])) {

//     $idnovel = $_POST['idnovel'];
// }

// if (isset($_POST['content'])) {

//     $content = $_POST['content'];
// }

// $comment = new Message();
// $comment->__construct();
// $response = array();
// $response = json_encode($comment->newComment($idnovel, $idacc, $content));
// echo $response;

require_once 'bookmark.php';


$idacc = "5";

$idnovel = "2";

$idchapter = "4";

if (isset($_POST['idacc'])) {

    $idacc = $_POST['idacc'];
}

if (isset($_POST['idnovel'])) {

    $idnovel = $_POST['idnovel'];
}

if (isset($_POST['idchapter'])) {

    $idchapter = $_POST['idchapter'];
}

$bookmark = new BookMark();

$response = array();
$response = json_encode($bookmark->makecheckHistory($idacc, $idnovel, $idchapter));
echo $response;

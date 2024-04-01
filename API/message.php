<?php
include 'dbConnect.php';

class Message
{
    private $db;
    private $db_table = "comment";
    public function __construct()
    {
        $this->db = new DbConnect();
    }
    public function isIdnovelExit($idnovel)
    {
        $query = "select ID from novel WHERE ID = '$idnovel'";
        $result = mysqli_query($this->db->getDb(), $query);
        if (mysqli_num_rows($result) > 0) {

            return true;
        }
        mysqli_close($this->db->getDb());
        return false;
    }
    public function isIdaccExit($idacc)
    {
        $query = "select ID from account WHERE ID = '$idacc'";
        $result = mysqli_query($this->db->getDb(), $query);
        if (mysqli_num_rows($result) > 0) {

            return true;
        }
        mysqli_close($this->db->getDb());
        return false;
    }
    public function newComment($idnovel, $idacc, $content)
    {
        if ($this->isIdnovelExit($idnovel) == true && $this->isIdaccExit($idacc) == true) {
            $query = "INSERT INTO ".$this->db_table." (`IDnovel`, `IDacc`, `Content`) VALUES ('$idnovel','$idacc','$content')";
            $inserted = mysqli_query($this->db->getDb(), $query);
            if ($inserted == true) {

                $json['success'] = true;
                $json['message'] = "Successfully sent";
            } else {

                $json['success'] = false;
                $json['message'] = "Error in sending comment";
            }
            mysqli_close($this->db->getDb());
        } else {
            $json['success'] = false;
            $json['message'] = "Can not find user or novel to sent comment";
        }
        return $json;
    }
}

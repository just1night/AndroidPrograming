<?php

include 'dbConnect.php';

class Novel
{
    private $db;
    private $db_table = "novel";

    public function __construct()
    {
        $this->db = new DbConnect();
    }

    public function getTop5()
    {
        $query = "SELECT ID,name,img,author,discription FROM " . $this->db_table . " LIMIT 5";

        $result = mysqli_query($this->db->getDb(), $query);

        if (mysqli_num_rows($result) > 0) {

            $data = array();

            while ($row = mysqli_fetch_assoc($result)) {
                $data[] = $row;
            }
            mysqli_close($this->db->getDb());

            return $data;
        }
        mysqli_close($this->db->getDb());
        return false;
    }
}

<?php
require "init.php";
$token=$_POST["token"];
$name=$_POST["name"];
$sql="UPDATE `user` SET `Token`='$token' WHERE `Name`='$name'";
if(mysqli_query($con,$sql))
echo "Success";
?>

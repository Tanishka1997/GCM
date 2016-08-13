<?php
require "init.php";
$name=$_POST["name"];
$token=$_POST["token"];
$sql="INSERT INTO `user` (`Name`,`Token`) VALUES ('$name','$token')";
if(mysqli_query($con,$sql))
echo "Success";
?>

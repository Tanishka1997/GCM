<?php
require "init.php";
$mobile=$_POST["mobile"];
$token=$_POST["token"];

$q="SELECT * FROM `user` WHERE `MOBILE`='$mobile'";
$result=mysqli_query($con,$q);
if(mysqli_num_rows($result)>0){
$sql="UPDATE `user` SET `Token`='$token' WHERE `Mobile`='$mobile'";
if(mysqli_query($con,$sql))
echo "Success";
}
else{
$sql="INSERT INTO `user` (`Mobile`,`Token`) VALUES ('$mobile','$token')";
if(mysqli_query($con,$sql))
echo "Success";
}
//mysqli_close($con);
?>

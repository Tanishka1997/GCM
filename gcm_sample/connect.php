<?php

require "init.php";
$message=$_POST["message"];
$mobile=$_POST["mobile"];
$key=""//yr  api;
$headers=array('Authorization:key='.$key,'Content-Type:application/json');
$sql="SELECT `Token` FROM `user` WHERE `Mobile`='$mobile'";
$result=mysqli_query($con,$sql);
$url="https://gcm-http.googleapis.com/gcm/send";
if(mysqli_num_rows($result)>0){
while($row=mysqli_fetch_assoc($result)){
$token=$row["Token"];
}
}

/*
$key="AIzaSyCMtXTFzGE9HOFoB4hxlChsJV1EC2Tth5Y";
$headers=array('Authorization:key='.$key,'Content-Type:application/json');
$message="Hello";
$token=$_POST["token"];
$url="https://gcm-http.googleapis.com/gcm/send";
*/

$fields=array('data'=>array("message"=>$message),'to'=>$token);
$ch=curl_init();
curl_setopt($ch,CURLOPT_URL,$url);
curl_setopt($ch,CURLOPT_POST,true);
curl_setopt($ch,CURLOPT_HTTPHEADER,$headers);
curl_setopt($ch,CURLOPT_RETURNTRANSFER,true);
curl_setopt($ch,CURLOPT_SSL_VERIFYPEER,false);
curl_setopt($ch,CURLOPT_POSTFIELDS,json_encode($fields));
$result=curl_exec($ch);
curl_close($ch);
mysqli_close($con);
?>

<?php
require "init.php";
$message="i m tanishka";
$name=$_POST["name"];
$key="AIzaSyCMtXTFzGE9HOFoB4hxlChsJV1EC2Tth5Y";
$headers=array('Authorization:key='.$key,'Content-Type:application/json');
$sql="SELECT `Token` FROM `user` WHERE `Name`='$name'";
$result=mysqli_query($con,$sql);
if(mysqli_mun_rows($result)>0){
while($row=mysqli_fetch_assoc($result)){
$token=$row["Token"];
}
}
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

echo $result;
?>

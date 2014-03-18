<!DOCTYPE html>
<!-- To run the current sample code in your own environment, copy this to an html page. -->

<html>
<head>
	 <script src="js/jquery.js"></script>
    <script src="js/jsviews.js"></script>
  
</head>
<body>

<div id="result"></div>

<script id="theTmpl" type="text/x-jsrender">
<div>
   <em>Name:</em> {{:name}}
   {{if showNickname && nickname}}
      (Goes by <em>{{:nickname}}</em>)
   {{/if}}
</div>
</script>

<script type="text/javascript">
var data = [
  {
    "name": "Robert",
    "nickname": "Bob",
    "showNickname": true
  },
  {
    "name": "Susan",
    "nickname": "Sue",
    "showNickname": false
  }
];

var template = $.templates("#theTmpl");
template.link("#result", data);

</script>

</body>
</html>

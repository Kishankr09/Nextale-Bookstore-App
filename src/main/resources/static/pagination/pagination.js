$(document).ready(function () {
  console.log("ready!");
  changePageAndSize();
});

function changePageAndSize() {
  $("#pageSizeSelect").change(function (evt) {
    var url = window.location.href;
    console.log(this.value);
    var pageSizeParam = "pageSize=" + this.value;
    var pageParam = "page=1";
    if (url.indexOf("?") !== -1) {
      // URL already has a query string
      if (url.indexOf("pageSize=") === -1 && url.indexOf("page=") === -1) {
        // Query string does not have pageSize or page parameters
        url += "&" + pageSizeParam + "&" + pageParam;
      } else if (url.indexOf("pageSize=") === -1) {
        // Query string does not have pageSize parameter
        url += "&" + pageSizeParam;
      } else if (url.indexOf("page=") === -1) {
        // Query string does not have page parameter
        url += "&" + pageParam;
      } else {
        // Query string already has pageSize and page parameters
        console.log("url before: " + url);
        console.log("pageSizeParam: " + pageSizeParam);
        url = url.replace(/([&?])pageSize=-?\d+/, "$1" + pageSizeParam);
        url = url.replace(/page=\d+/, pageParam);
        console.log("url after: " + url);
      }
    } else {
      // URL does not have a query string
      url += "?" + pageSizeParam + "&" + pageParam;
    }
    window.location.href = url;
  });
}

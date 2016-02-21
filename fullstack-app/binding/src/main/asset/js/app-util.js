
if (typeof String.prototype.capFirst != 'function') {
  String.prototype.capFirst = function () {
    var s = this.toLowerCase();
    return s.charAt(0).toUpperCase() + s.slice(1);
  };
}

if (typeof String.prototype.startsWith != 'function') {
  // see below for better implementation!
  String.prototype.startsWith = function (str) {
    return this.slice(0, str.length) == str;
  };
}

if (typeof String.prototype.endsWith != 'function') {
  String.prototype.endsWith = function (str) {
    return this.slice(-str.length) == str;
  };
}

if (typeof String.prototype.contains != 'function') {
  String.prototype.contains = function (str) {
    return this.indexOf(str) != -1;
  };
}

if (typeof String.prototype.trim != 'function') {
  String.prototype.trim = function () {
    return (this.replace(/^[\s\xA0]+/, "").replace(/[\s\xA0]+$/, ""));
  };
}

if (typeof String.prototype.isEmail != 'function') {
  String.prototype.isEmail = function (str) {
    // http://stackoverflow.com/a/46181/11236
    var re = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
    return re.test(this);
  };
}

if (typeof String.prototype.hashCode != 'function') {
  String.prototype.hashCode = function() {
    var hash = 0, i, chr, len;
    if (this.length === 0) return hash;
    for (i = 0, len = this.length; i < len; i++) {
      chr   = this.charCodeAt(i);
      hash  = ((hash << 5) - hash) + chr;
      hash |= 0; // Convert to 32bit integer
    }
    return hash;
  };
}

if (typeof Array.isArray === 'undefined') {
  Array.isArray = function(obj) {
    return Object.prototype.toString.call(obj) === '[object Array]';
  }
};

if (typeof String.prototype.withQueryParam != 'function') {
  String.prototype.withQueryParam = function(name, val) {
    if (!name) {
      return this;
    }
    var ret = this, hash = '';
    if (this.indexOf('#') > -1) {
      var a = this.split('#');
      ret = a[0];
      hash = '#' + a[1];
    }
    var hasQuery = ret.indexOf('?') > -1;
    if (!hasQuery) {
      ret = ret + "?_=_";
    }
    if (name instanceof Object && !Array.isArray(name)) {
      for (var k in name) {
        if (name.hasOwnProperty(k) && !ret.contains('&' + k + '=') && !ret.contains('?' + k + '=')) {
          ret = ret + "&" + k + "=" + name[k];
        }
      }
    } else {
      ret = ret + "&" + name + "=" +val;
    }
    return ret + hash;
  }
}

// allows it to do something like
// var tmpl = "hello {0}"
// var msg = tmpl.fmt("world")
if (typeof String.prototype.fmt != 'function') {
  String.prototype.fmt = function(str) {
    var fmt = this;
    var args = Array.prototype.slice.call(arguments);
    var sprintfRegex = /\{(\d+)\}/g;
    var sprintf = function (match, number) {
      return number in args ? args[number] : match;
    };
    return fmt.replace(sprintfRegex, sprintf);
  };
}


var callbackWithAjaxRedirect = function(realCallback) {
  return function() {
    var jqXHR = arguments[2];
    if (jqXHR.status == 278) {
      window.location = jqXHR.getResponseHeader("Location");
    }
    if (realCallback) realCallback.apply(this, arguments);
  }
}

jQuery.each(["get", "post", "put", "delete" ], function (i, method) {
  jQuery[ method ] = function (url, data, callback, type) {
    // shift arguments if data argument was omitted
    if (jQuery.isFunction(data)) {
      type = type || callback;
      callback = data;
      data = undefined;
    }

    if (method == "put") {
      var hasParam = url.contains("?");
      if (!hasParam) url = url + "?___=___";
      if (data) $.each(data, function(key, val){
        url = url + "&" + encodeURIComponent(key) + "=" + encodeURIComponent(val);
      });
      data = {};
    }

    return jQuery.ajax({
      url: url,
      type: method,
      dataType: type,
      data: data,
      success: callbackWithAjaxRedirect(callback)
    });
  };
});

jQuery.each(["getJSON", "postJSON", "putJSON", "deleteJSON"], function (i, method) {
  jQuery[ method ] = function (url, data, callback) {
    if (jQuery.isFunction(data)) {
      callback = data;
      data = undefined;
    }

    if (method.startsWith("put")) {
      var hasParam = url.contains("?");
      if (!hasParam) url = url + "?___=___";
      if (data) $.each(data, function(key, val){
        url = url + "&" + encodeURIComponent(key) + "=" + encodeURIComponent(val);
      });
      data = {};
    }
    if (method.startsWith("get")) {
      var hasParam = url.contains("?");
      if (!hasParam)
      {
        url = url +"?now="+ new Date().getTime();
      }
      else
      {
        url = url +"&now="+ new Date().getTime();
      }
    }
    return jQuery.ajax({
      url: url,
      type: method.replace("JSON", ""),
      dataType: "json",
      data: data,
      success: callbackWithAjaxRedirect(callback)
    });
  };
});

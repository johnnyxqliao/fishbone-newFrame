function sideBarHide() {
  // $("#projectmanage").trigger("click");
}

// window.onload = function(){
//   var canvas = document.getElementById('main');
//   function onMouseEvent(event){
//     $("#projectmanage").trigger("click");
//   }
//   canvas.addEventListener("mouseup",onMouseEvent,false);
// };
// !function(){
//   $("#projectmanage").trigger("click");
// }();
// !function(b){
//   alert(3);
// }(4);
// !function(){
//   var canvas = document.getElementById('main');
//   function onMouseEvent(event){
//     $("#projectmanage").trigger("click");
//   }
//   canvas.addEventListener("mouseup",onMouseEvent,false);
// }(),

!function (a) {
  "ace" in window || (window.ace = {}),
  "helper" in window.ace || (window.ace.helper = {}),
  "vars" in window.ace || (window.ace.vars = {}),
     window.ace.vars.icon = " ace-icon ",
     window.ace.vars[".icon"] = ".ace-icon",
     ace.vars.touch = "ontouchstart" in window;
  var b = navigator.userAgent;
  ace.vars.webkit = !!b.match(/AppleWebKit/i),
     ace.vars.safari = !!b.match(/Safari/i) && !b.match(/Chrome/i),
     ace.vars.android = ace.vars.safari && !!b.match(/Android/i),
     ace.vars.ios_safari = !!b.match(/OS ([4-9])(_\d)+ like Mac OS X/i) && !b.match(/CriOS/i),
     ace.vars.ie = window.navigator.msPointerEnabled || document.all && document.querySelector,
     ace.vars.old_ie = document.all && !document.addEventListener,
     ace.vars.very_old_ie = document.all && !document.querySelector,
     ace.vars.firefox = "MozAppearance" in document.documentElement.style,
     ace.vars.non_auto_fixed = ace.vars.android || ace.vars.ios_safari,
     ace.click_event = ace.vars.touch && jQuery.fn.tap ? "tap" : "click"
}(),
   function (a, b) {
     function c(b, c) {
       function e() {
         this.mobile_view = this.mobile_style < 4 && this.is_mobile_view(),
            this.collapsible = !this.mobile_view && this.is_collapsible(),
            this.minimized = !this.collapsible && this.$sidebar.hasClass(k) || 3 == this.mobile_style && this.mobile_view && this.$sidebar.hasClass(l),
            this.horizontal = !(this.mobile_view || this.collapsible) && this.$sidebar.hasClass(n)
       }

       var f = this;
       this.$sidebar = a(b),
          this.$sidebar.attr("data-sidebar", "true"),
       this.$sidebar.attr("id") || this.$sidebar.attr("id", "id-sidebar-" + ++d);
       var g = ace.helper.getAttrSettings(b, a.fn.ace_sidebar.defaults, "sidebar-");
       this.settings = a.extend({}, a.fn.ace_sidebar.defaults, c, g),
          this.minimized = !1,
          this.collapsible = !1,
          this.horizontal = !1,
          this.mobile_view = !1,
          this.vars = function () {
            return {
              minimized: this.minimized,
              collapsible: this.collapsible,
              horizontal: this.horizontal,
              mobile_view: this.mobile_view
            }
          }
          ,
          this.get = function (a) {
            return this.hasOwnProperty(a) ? this[a] : void 0
          }
          ,
          this.set = function (a, b) {
            this.hasOwnProperty(a) && (this[a] = b)
          }
          ,
          this.ref = function () {
            return this
          }
       ;
       var h = function (b, c) {
         var d, e, f = a(this).find(ace.vars[".icon"]);
         if (f.length > 0) {
           d = f.attr("data-icon1"),
              e = f.attr("data-icon2"),
              "undefined" != typeof b ? b ? f.removeClass(d).addClass(e) : f.removeClass(e).addClass(d) : f.toggleClass(d).toggleClass(e);
           try {
             c !== !1 && ace.settings.saveState(f.get(0))
           } catch (g) {
           }
         }
       }
          , i = function () {
         var b = f.$sidebar.find(".sidebar-collapse");
         return 0 == b.length && (b = a('.sidebar-collapse[data-target="#' + (f.$sidebar.attr("id") || "") + '"]')),
            b = 0 != b.length ? b[0] : null
       };
       this.toggleMenu = function (c, d) {
         if (this.collapsible)
           return !1;
         this.minimized = !this.minimized;
         var d = !(c === !1 || d === !1);
         this.minimized ? this.$sidebar.addClass("menu-min") : this.$sidebar.removeClass("menu-min");
         try {
           d && ace.settings.saveState(b, "class", "menu-min", this.minimized)
         } catch (e) {
         }
         return c || (c = i()),
         c && h.call(c, this.minimized, d),
         ace.vars.old_ie && ace.helper.redraw(b),
            a(document).trigger("settings.ace", ["sidebar_collapsed", this.minimized, b, d]),
            this.minimized ? this.$sidebar.trigger(a.Event("collapse.ace.sidebar")) : this.$sidebar.trigger(a.Event("expand.ace.sidebar")),
            !0
       }
          ,
          this.collapse = function (a, b) {
            this.collapsible || (this.minimized = !1,
               this.toggleMenu(a, b))
          }
          ,
          this.expand = function (a, b) {
            this.collapsible || (this.minimized = !0,
               this.toggleMenu(a, b))
          }
          ,
          this.showResponsive = function () {
            this.$sidebar.removeClass(l).removeClass(m)
          }
          ,
          this.toggleResponsive = function (b, c) {
            if (this.mobile_view && 3 == this.mobile_style) {
              if (this.$sidebar.hasClass("menu-min")) {
                this.$sidebar.removeClass("menu-min");
                var d = i();
                d && h.call(d)
              }
              var c = "boolean" == typeof c ? c : "boolean" == typeof b ? b : this.$sidebar.hasClass(l);
              if (c ? this.$sidebar.addClass(m).removeClass(l) : this.$sidebar.removeClass(m).addClass(l),
                    this.minimized = !c,
                 b && "object" == typeof b || (b = this.$sidebar.find(".sidebar-expand"),
                 0 == b.length && (b = a('.sidebar-expand[data-target="#' + (this.$sidebar.attr("id") || "") + '"]')),
                    b = 0 != b.length ? b[0] : null),
                    b) {
                var e, g, j = a(b).find(ace.vars[".icon"]);
                j.length > 0 && (e = j.attr("data-icon1"),
                   g = j.attr("data-icon2"),
                   c ? j.removeClass(e).addClass(g) : j.removeClass(g).addClass(e))
              }
              c ? f.$sidebar.trigger(a.Event("mobileShow.ace.sidebar")) : f.$sidebar.trigger(a.Event("mobileHide.ace.sidebar")),
                 a(document).triggerHandler("settings.ace", ["sidebar_collapsed", this.minimized])
            }
          }
          ,
          this.is_collapsible = function () {
            var b;
            return this.$sidebar.hasClass("navbar-collapse") && null != (b = a('.navbar-toggle[data-target="#' + (this.$sidebar.attr("id") || "") + '"]').get(0)) && b.scrollHeight > 0
          }
          ,
          this.is_mobile_view = function () {
            var b;
            return null != (b = a('.menu-toggler[data-target="#' + (this.$sidebar.attr("id") || "") + '"]').get(0)) && b.scrollHeight > 0
          }
       ;
       var j = !1;
       this.show = function (b, c, d) {
         if (d = d !== !1,
            d && j)
           return !1;
         var e, f = a(b);
         if (f.trigger(e = a.Event("show.ace.submenu")),
               e.isDefaultPrevented())
           return !1;
         d && (j = !0),
            c = "undefined" != typeof c ? c : this.settings.duration,
            f.css({
              height: 0,
              overflow: "hidden",
              display: "block"
            }).removeClass("nav-hide").addClass("nav-show").parent().addClass("open"),
            b.scrollTop = 0;
         var g = function (b, c) {
           b && b.stopPropagation(),
              f.css({
                "transition-property": "",
                "transition-duration": "",
                overflow: "",
                height: ""
              }),
           c !== !1 && f.trigger(a.Event("shown.ace.submenu")),
           d && (j = !1)
         }
            , h = b.scrollHeight;
         return 0 != c && 0 != h && a.support.transition.end ? (f.css({
           height: h,
           "transition-property": "height",
           "transition-duration": c / 1e3 + "s"
         }).one(a.support.transition.end, g),
         ace.vars.android && setTimeout(function () {
           g(null, !1),
              ace.helper.redraw(b)
         }, c + 20)) : g(),
            !0
       }
          ,
          this.hide = function (b, c, d) {
            if (d = d !== !1,
               d && j)
              return !1;
            var e, f = a(b);
            if (f.trigger(e = a.Event("hide.ace.submenu")),
                  e.isDefaultPrevented())
              return !1;
            d && (j = !0),
               c = "undefined" != typeof c ? c : this.settings.duration;
            var g = b.scrollHeight;
            f.css({
              height: g,
              overflow: "hidden",
              display: "block"
            }).parent().removeClass("open"),
               b.offsetHeight;
            var h = function (b, c) {
              b && b.stopPropagation(),
                 f.css({
                   display: "none",
                   overflow: "",
                   height: "",
                   "transition-property": "",
                   "transition-duration": ""
                 }).removeClass("nav-show").addClass("nav-hide"),
              c !== !1 && f.trigger(a.Event("hidden.ace.submenu")),
              d && (j = !1)
            };
            return 0 != c && 0 != g && a.support.transition.end ? (f.css({
              height: 0,
              "transition-property": "height",
              "transition-duration": c / 1e3 + "s"
            }).one(a.support.transition.end, h),
            ace.vars.android && setTimeout(function () {
              h(null, !1),
                 ace.helper.redraw(b)
            }, c + 20)) : h(),
               !0
          }
          ,
          this.toggle = function (a, b) {
            if (b = b || f.settings.duration,
               0 == a.scrollHeight) {
              if (this.show(a, b))
                return 1
            } else if (this.hide(a, b))
              return -1;
            return 0
          }
          ,
          this.mobileToggle = function (a) {
            this.mobile_view ? 1 == this.mobile_style || 2 == this.mobile_style ? this.toggleMobile("object" == typeof a ? a : null, "boolean" == typeof a ? a : null) : 3 == this.mobile_style && this.toggleResponsive("object" == typeof a ? a : null, "boolean" == typeof a ? a : null) : this.collapsible && this.toggleCollapsible("object" == typeof a ? a : null, "boolean" == typeof a ? a : null)
          }
          ,
          this.mobileShow = function () {
            this.mobileToggle(!0)
          }
          ,
          this.mobileHide = function () {
            this.mobileToggle(!1)
          }
          ,
          this.toggleMobile = function (b, c) {
            if (1 == this.mobile_style || 2 == this.mobile_style) {
              var c = "boolean" == typeof c ? c : "boolean" == typeof b ? b : !this.$sidebar.hasClass("display");
              b && "object" == typeof b || (b = a('.menu-toggler[data-target="#' + (this.$sidebar.attr("id") || "") + '"]'),
                 b = 0 != b.length ? b[0] : null),
                 c ? (this.$sidebar.addClass("display"),
                 b && a(b).addClass("display")) : (this.$sidebar.removeClass("display"),
                 b && a(b).removeClass("display")),
                 c ? f.$sidebar.trigger(a.Event("mobileShow.ace.sidebar")) : f.$sidebar.trigger(a.Event("mobileHide.ace.sidebar"))
            }
          }
          ,
          this.toggleCollapsible = function (b, c) {
            if (4 == this.mobile_style) {
              var c = "boolean" == typeof c ? c : "boolean" == typeof b ? b : !this.$sidebar.hasClass("in");
              c ? this.$sidebar.collapse("show") : (this.$sidebar.removeClass("display"),
                 this.$sidebar.collapse("hide")),
                 c ? f.$sidebar.trigger(a.Event("mobileShow.ace.sidebar")) : f.$sidebar.trigger(a.Event("mobileHide.ace.sidebar"))
            }
          }
       ;
       var k = "menu-min"
          , l = "responsive-min"
          , m = "responsive-max"
          , n = "h-sidebar"
          , o = function () {
         this.mobile_style = 1,
            this.$sidebar.hasClass("responsive") && !a('.menu-toggler[data-target="#' + this.$sidebar.attr("id") + '"]').hasClass("navbar-toggle") ? this.mobile_style = 2 : this.$sidebar.hasClass(l) ? this.mobile_style = 3 : this.$sidebar.hasClass("navbar-collapse") && (this.mobile_style = 4)
       };
       o.call(f),
          a(window).on("resize.sidebar.vars", function () {
            e.call(f)
          }).triggerHandler("resize.sidebar.vars"),
          this.$sidebar.on(ace.click_event + ".ace.submenu", ".nav-list", function (b) {
            // var c = this
            //    , d = a(b.target).closest("a");
            // if (d && 0 != d.length) {
            //   var e = f.minimized && !f.collapsible;
            //   if (d.hasClass("dropdown-toggle")) {
            //     b.preventDefault();
            //     var g = d.siblings(".submenu").get(0);
            //     if (!g)
            //       return !1;
            //     var h = a(g)
            //        , i = 0
            //        , j = g.parentNode.parentNode;
            //     if (e && j == c || h.parent().hasClass("hover") && "absolute" == h.css("position") && !f.collapsible)
            //       return !1;
            //     var k = 0 == g.scrollHeight;
            //     return k && f.settings.hide_open_subs && a(j).find("> .open > .submenu").each(function () {
            //       this == g || a(this.parentNode).hasClass("active") || (i -= this.scrollHeight,
            //          f.hide(this, f.settings.duration, !1))
            //     }),
            //        k ? (f.show(g, f.settings.duration),
            //        0 != i && (i += g.scrollHeight)) : (f.hide(g, f.settings.duration),
            //           i -= g.scrollHeight),
            //     0 != i && ("true" != f.$sidebar.attr("data-sidebar-scroll") || f.minimized || f.$sidebar.ace_sidebar_scroll("prehide", i)),
            //        !1
            //   }
            //   if ("tap" == ace.click_event && e && d.get(0).parentNode.parentNode == c) {
            //     var l = d.find(".menu-text").get(0);
            //     if (null != l && b.target != l && !a.contains(l, b.target))
            //       return b.preventDefault(),
            //          !1
            //   }
            //   if (ace.vars.ios_safari && "false" !== d.attr("data-link"))
            //     return document.location = d.attr("href"),
            //        b.preventDefault(),
            //        !1
            // }
          })
     }

     var d = 0;
     a(document).on(ace.click_event + ".ace.menu", ".menu-toggler", function (b) {
       var c = a(this)
          , d = a(c.attr("data-target"));
       if (0 != d.length) {
         b.preventDefault(),
            d.ace_sidebar("mobileToggle", this);
         var e = ace.click_event + ".ace.autohide"
            , f = "true" === d.attr("data-auto-hide");
         return c.hasClass("display") ? (f && a(document).on(e, function (b) {
           return d.get(0) == b.target || a.contains(d.get(0), b.target) ? void b.stopPropagation() : (d.ace_sidebar("mobileToggle", this, !1),
              void a(document).off(e))
         }),
         "true" == d.attr("data-sidebar-scroll") && d.ace_sidebar_scroll("reset")) : f && a(document).off(e),
            !1
       }
     }).on(ace.click_event + ".ace.menu", ".sidebar-collapse", function (b) {
       var c = a(this).attr("data-target")
          , d = null;
       c && (d = a(c)),
       null != d && 0 != d.length || (d = a(this).closest(".sidebar")),
       0 != d.length && (b.preventDefault(),
          d.ace_sidebar("toggleMenu", this))
     }).on(ace.click_event + ".ace.menu", ".sidebar-expand", function (b) {
       var c = a(this).attr("data-target")
          , d = null;
       if (c && (d = a(c)),
          null != d && 0 != d.length || (d = a(this).closest(".sidebar")),
          0 != d.length) {
         var e = this;
         b.preventDefault(),
            d.ace_sidebar("toggleResponsive", this);
         var f = ace.click_event + ".ace.autohide";
         "true" === d.attr("data-auto-hide") && (d.hasClass(responsive_max_class) ? a(document).on(f, function (b) {
           return d.get(0) == b.target || a.contains(d.get(0), b.target) ? void b.stopPropagation() : (d.ace_sidebar("toggleResponsive", e),
              void a(document).off(f))
         }) : a(document).off(f))
       }
     }),
        a.fn.ace_sidebar = function (d, e, f) {
          var g, h = this.each(function () {
            var h = a(this)
               , i = h.data("ace_sidebar")
               , j = "object" == typeof d && d;
            i || h.data("ace_sidebar", i = new c(this, j)),
            "string" == typeof d && "function" == typeof i[d] && (g = e instanceof Array ? i[d].apply(i, e) : f !== b ? i[d](e, f) : i[d](e))
          });
          return g === b ? h : g
        }
        ,
        a.fn.ace_sidebar.defaults = {
          duration: 300,
          hide_open_subs: !0
        }
   }(window.jQuery);
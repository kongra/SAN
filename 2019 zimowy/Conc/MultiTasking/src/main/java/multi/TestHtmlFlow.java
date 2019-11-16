package multi;

import htmlflow.StaticHtml;

public class TestHtmlFlow {

  public static String getHTML() {
    return StaticHtml
        .view()
        .html()
        .head()
        .title().text("HtmlFlow").__()
        .__() //head
        .body()
        .div().attrClass("container")
        .h1().text("My first page with HtmlFlow").__()
        .img().attrSrc("https://avatars1.githubusercontent.com/u/35267172").__()
        .p().text("Typesafe is awesome! :-)").__()
        .__()
        .__() //body
        .__() //html
        .render();
  }

}

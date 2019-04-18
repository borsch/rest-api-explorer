<html>
    <head>
        <style><#include "/assets/css/materialize.min.css"></style>
        <style><#include "/assets/css/style.css"></style>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <nav class="light-blue lighten-1" role="navigation">
            <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>
                <ul class="right hide-on-med-and-down">
                    <li><a href="#">Navbar Link</a></li>
                </ul>

                <ul id="nav-mobile" class="sidenav">
                    <li><a href="#">Navbar Link</a></li>
                </ul>
                <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
            </div>
        </nav>
        <div class="container">
            <div class="section">

                <ul class="collapsible">
                    <#list controllers as ctrl>
                        <li>
                            <div class="collapsible-header method-description">
                                <p>
                                    <strong>${ctrl.name} (${ctrl.apiDescription.path()})</strong>
                                </p>
                                <p class="endpoint-description">
                                    ${ctrl.apiDescription.description()}
                                </p>
                            </div>
                            <div class="collapsible-body">
                                <ul class="collapsible">
                                    <#list ctrl.endpoints as endpoint>
                                        <li>
                                            <div class="collapsible-header method-description">
                                                <p>
                                                    <strong>${endpoint.annotation.path()} : HTTP ${endpoint.annotation.method()}</strong>
                                                </p>
                                                <p class="endpoint-description">
                                                    ${endpoint.annotation.description()}
                                                </p>
                                            </div>
                                            <div class="collapsible-body method-body">

                                            </div>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                        </li>
                    </#list>
                </ul>

            </div>
            <br><br>
        </div>

        <footer class="page-footer orange">
            <div class="container">
                <div class="row">
                    <div class="col l6 s12">
                        <h5 class="white-text">Company Bio</h5>
                        <p class="grey-text text-lighten-4">We are a team of college students working on this project like it's our full time job. Any amount would help support and continue development on this project and is greatly appreciated.</p>


                    </div>
                    <div class="col l3 s12">
                        <h5 class="white-text">Settings</h5>
                        <ul>
                            <li><a class="white-text" href="#!">Link 1</a></li>
                            <li><a class="white-text" href="#!">Link 2</a></li>
                            <li><a class="white-text" href="#!">Link 3</a></li>
                            <li><a class="white-text" href="#!">Link 4</a></li>
                        </ul>
                    </div>
                    <div class="col l3 s12">
                        <h5 class="white-text">Connect</h5>
                        <ul>
                            <li><a class="white-text" href="#!">Link 1</a></li>
                            <li><a class="white-text" href="#!">Link 2</a></li>
                            <li><a class="white-text" href="#!">Link 3</a></li>
                            <li><a class="white-text" href="#!">Link 4</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="footer-copyright">
                <div class="container">
                    Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
                </div>
            </div>
        </footer>

        <script><#include "/assets/js/jquery.min.js"></script>
        <script><#include "/assets/js/materialize.min.js"></script>
        <script><#include "/assets/js/script.js"></script>
    </body>
</html>
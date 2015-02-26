<!-- Footer -->
<% int year = Calendar.instance.get(Calendar.YEAR) %>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="list-inline">
                    <li>
                        <a href="${createLink(uri: "/")}"> <g:message code="menu.home.label"/> </a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="#about"><g:message code="menu.about.label"/></a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="#services"><g:message code="menu.services.label"/></a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="#contact"><g:message code="menu.contact.label"/></a>
                    </li>
                </ul>
                <p class="copyright text-muted small">Copyright &copy; ADVENTicket ${year}. <g:message code="menu.footer.allright.label"/></p>
            </div>
        </div>
    </div>
</footer>
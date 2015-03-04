<recaptcha:ifEnabled>
<%--<g:if test="${session.idioma == 'es'}"> --%>
    <recaptcha:recaptcha theme="white" lang="es"/>
<%--</g:if>
<g:else>
    <script type="text/javascript">
        var RecaptchaOptions = {
            custom_translations : {
                instructions_visual : "Type the two words:",
                instructions_audio : "Write what you hear:",
                refresh_btn : "Get a new word",
                audio_challenge : "Audio mode",
                visual_challenge : "Visual mode",
                help_btn : "Help",
                incorrect_try_again : "Wrong, try again:",
            },
            theme:'white',
            lang:'en'
        }
    </script>
    <recaptcha:recaptcha/>
</g:else> --%>
</recaptcha:ifEnabled>
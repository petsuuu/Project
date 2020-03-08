Feature: NOME DA FEATURE

 Scenario: NOME DO CENARIO
        Given I am connected in session "pw3270:A", address "192.168.101.1:1025"
        When I access the "AC" environment
        Then I am able to see the text "Visite nosso Site: www.serasa.com.br" at row "14" and column "21"

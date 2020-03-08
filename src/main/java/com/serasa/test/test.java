package com.serasa.test;

import org.junit.Assert;

import com.serasa.common.utils.mainframe.Mainframe;

import java.io.IOException;


public class test extends Mainframe {

	public static void main(String[] args) throws IOException {

		//abrindo o emualdor
		Runtime.getRuntime().exec("C:\\Program Files\\pw3270\\pw3270.exe");

		//conectando na sessão do mainframe
		mfConnect("pw3270:A", "192.168.101.1:1025", 10);

		//inserindo um valor no emulador, passando linha , coluna, e o texto que deseja inserir
		setText(23, 7, "AC");

		//pressionando ENTER
		Enter();

		System.out.println(getRevision());

// validando um texto na tela do emulador
		Assert.assertEquals("Visite nosso Site: www.serasa.com.br", getText(14, 21, 36));

		//recuperando um valor na tela do mainframe e exibindo no console
		System.out.println(getText(14, 21, 36));

		//exibindo a tela inteira do emulador no console
		System.out.println(getScreen());

		//pressioanndo F3
		pfKey(3);

		//desconectando do emulador
		disconnect();
	}
}

package br.edu.utfpr.tcc.services;

public interface S3Services {
	//public void downloadFile(String keyName);
	public String uploadFile(String keyName, String uploadFilePath);
}

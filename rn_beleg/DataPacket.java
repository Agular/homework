
public class DataPacket extends Packet{
	byte[] data;
	
	public DataPacket(short sessionNr, int packetNr, byte[] data){
		super.sessionNr = sessionNr;
		super.packetNr = packetNr;
		this.data = data;
	}
	public DataPacket (byte[] data){
		super.sessionNr = (short)( ((data[0]&0xFF)<<8) | (data[1]&0xFF) );
		super.packetNr = (int) data[2];
		this.data = new byte[data.length-3];
		System.arraycopy(data, 3, this.data, 0, data.length-3);
	}
	@Override
	byte[] returnData() {
		byte[] data = new byte[this.data.length+3];
		data[0] = (byte) (sessionNr >> 8);
		data[1] = (byte) (sessionNr & 0xFF);
		data[2] = (byte) packetNr;
		System.arraycopy(this.data, 0, data, 3, this.data.length);
		return data;
	}
	byte[] returnSendData(){
		return this.data;
	}
}
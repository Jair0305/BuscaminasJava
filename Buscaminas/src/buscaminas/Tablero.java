package buscaminas;

import java.util.LinkedList;
import java.util.List;

public class Tablero {
	
Casilla[][] casillas;
    
    int numFilas;
    int numColumnas;
    int numMinas;

    public Tablero(int numFilas, int numColumnas, int numMinas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.numMinas = numMinas;
        
        initCasillas();
    }
    
    public void initCasillas()
    {
        casillas = new Casilla[this.numFilas][this.numColumnas];
        
        for(int i = 0; i < casillas.length; i++)
        {
            for(int j = 0; j<casillas[i].length;j++)
            {
                casillas[i][j] = new Casilla(i,j);
            }
        }
        
        generacionDeMinas();
    }
    
    private void generacionDeMinas()
    {
        int minasGeneradas = 0;
        
        while(minasGeneradas!=numMinas)
        {
            int posFila =(int)(Math.random()*casillas.length);
            int posColumna =(int)(Math.random()*casillas[0].length);
            
            if(!casillas[posFila][posColumna].isMina())
            {
                casillas[posFila][posColumna].setMina(true);
                minasGeneradas++;
            }
        }
        actualizarNumeroMinasAlrededor();
    }
    
    public void mostrarTablero()
    {
        for(int i = 0; i < casillas.length; i++)
        {
            for(int j = 0; j<casillas[i].length;j++)
            {
                System.out.print(casillas[i][j].isMina()?"*":"0");
            }
            System.out.println("");
        }
    }
    
    public void mostrarPistas()
    {
        for(int i = 0; i < casillas.length; i++)
        {
            for(int j = 0; j<casillas[i].length;j++)
            {
                System.out.print(casillas[i][j].getMinasAlrededor());
            }
            System.out.println("");
        }
    }
    
    private void actualizarNumeroMinasAlrededor()
    {
    	for(int i = 0; i < casillas.length; i++)
        {
            for(int j = 0; j<casillas[i].length;j++)
            {
                if(casillas[i][j].isMina())
                {
                	List<Casilla> casillasAlrededor = obtenerCasillaAlrededor(i, j);
                	casillasAlrededor.forEach((c)->c.inrementarNumeroMinasAlrededor());
                }
            }
        }
    }
    
    private List<Casilla> obtenerCasillaAlrededor(int posFila, int posColumna)
    {
    	List<Casilla> listaCasillas = new LinkedList<>();
    	for(int i = 0; i<8;i++)
    	{
    		int tmpPosFila=posFila;
    		int tmpPosColumna=posColumna;
    		switch(i)
    		{
    			case 0:
    				tmpPosFila--;
    				break;
    			case 1: 
    				tmpPosFila--;
    				tmpPosColumna++;
    				break;
    			case 2:
    				tmpPosColumna++;
    				break;
    			case 3: 
    				tmpPosColumna++;
    				tmpPosFila++;
    				break;
    			case 4: 
    				tmpPosFila++;
    				break;
    			case 5: 
    				tmpPosFila++;
    				tmpPosColumna--;
    				break;
    			case 6: 
    				tmpPosColumna--;
    				break;
    			case 7: 
    				tmpPosFila--;
    				tmpPosColumna--;
    				break;
    		}
    		
    		if(tmpPosFila>=0 && tmpPosFila<this.casillas.length && tmpPosColumna>=0 && tmpPosColumna<this.casillas[0].length)
    		{
    			listaCasillas.add(this.casillas[tmpPosFila][tmpPosColumna]);
    		}
    	}
    	return listaCasillas;
    }
    
    public static void main(String[] args) {
        Tablero tablero = new Tablero(6,6,5);
        tablero.mostrarTablero();
        System.out.println("---");
        tablero.mostrarPistas();
    }
}

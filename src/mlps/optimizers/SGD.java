package mlps.optimizers;

import mlps.MLP;
import matrices.BiasVector;
import matrices.GradientMatrix;

import mlps.Layer;

import static mlps.MLP.BackProResult;

/**
 * Voir <a href="https://en.wikipedia.org/wiki/Stochastic_gradient_descent">Descente de Gradient Stochastique</a>.
 * Optimizer le plus simple, mltiplie les gradients par un learning rate avant de les soustraire aux paramètres du réseau.
 */
public class SGD extends Optimizer {

    public final double learningRate;

    /**
     *
     * @param learningRate Le taux d'apprentissage utilisé par la SGD.
     */
    public SGD(double learningRate) {
        this.learningRate = learningRate;
    }

    @Override
    public void updateParameters(BackProResult gradients, MLP mlp) {

        for(int l = 0; l < mlp.getLayers().size(); l++ ) {

           // gradients.getWeightGradient(l).printNorm();

            GradientMatrix weightCorrection = gradients.getWeightGradient(l).multiply(learningRate);
            BiasVector biasGradient = gradients.getBiasGradient(l).multiply(learningRate);

            Layer layer = mlp.getLayer(l);

            layer.getWeightMatrix().substract(weightCorrection);
            layer.getBiasVector().substract(biasGradient);
        }

    }
}

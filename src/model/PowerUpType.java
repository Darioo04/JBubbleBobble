package model;

public enum PowerUpType {
	PINK_CANDY{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(100);
		}
	},
	BLUE_CANDY{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(100);
		}
	},
	YELLOW_CANDY{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(100);
		}
	},
	SHOES{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(100);
		}
	},
	CLOCK{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(200);
		}
	},
	DYNAMITE{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(200);
		}
	},
	CHACK_HEART{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(3000);
		}
	},
	CRYSTAL_RING{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(1000);
		}
	},
	AMETHYST_RING{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(1000);
		}
	},
	RUBY_RING{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(1000);
		}
	},
	CROSS_OF_THUNDER{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(3000);
		}
	},
	BLUE_LAMP{
		@Override
		public void applyPowerUp(Player player) {
			player.addScore(3000);
		}
	};
	
	public abstract void applyPowerUp(Player player);
	
	
}

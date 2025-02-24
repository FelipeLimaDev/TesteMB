package com.fdlr.testemb.coin.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage
import com.fdlr.testemb.coin.R
import com.fdlr.testemb.coreds.ui.MBDesignSystem
import com.fdlr.testemb.coreds.utils.toBrazilianCurrency
import com.fdlr.testemb.domain.model.Rate

@Composable
internal fun CoinItem(
    index: Int,
    rate: Rate, onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (index % 2 == 0) MBDesignSystem.colors.background
                else MBDesignSystem.colors.surface
            )
            .clickable(onClick = onClick)
            .padding(MBDesignSystem.spacing.spacing16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rate.id.toString(), modifier = Modifier.weight(1f),
            style = MBDesignSystem.typography.labelSmallRegular,
            color = MBDesignSystem.colors.textSecondary
        )
        Row(
            modifier = Modifier.weight(4f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = if (rate.imageUrl.isNullOrBlank()) R.drawable.ic_default_cryto else rate.imageUrl,
                contentDescription = rate.assetIdQuote,
                modifier = Modifier
                    .size(MBDesignSystem.spacing.spacing24)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.size(MBDesignSystem.spacing.spacing4))
            Text(
                text = rate.assetIdQuote,
                style = MBDesignSystem.typography.labelSmallBold,
                color = MBDesignSystem.colors.textPrimary
            )
        }

        Text(
            text = rate.rate.toBrazilianCurrency(), modifier = Modifier.weight(5f),
            style = MBDesignSystem.typography.labelSmallRegular,
            color = MBDesignSystem.colors.textPrimary
        )
    }
}